package com.PeaceOfMind.MicroMarket.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.dtos.DetalleVentaDTO;
import com.PeaceOfMind.MicroMarket.dtos.VentaDTO;
import com.PeaceOfMind.MicroMarket.dtos.VentaMapper;

import com.PeaceOfMind.MicroMarket.entity.DetalleVenta;
import com.PeaceOfMind.MicroMarket.entity.Empleado;
import com.PeaceOfMind.MicroMarket.entity.Product;
import com.PeaceOfMind.MicroMarket.entity.Venta;

import com.PeaceOfMind.MicroMarket.exceptions.BadRequestException;
import com.PeaceOfMind.MicroMarket.exceptions.NotFoundException;

import com.PeaceOfMind.MicroMarket.repositories.EmpleadoRepository;
import com.PeaceOfMind.MicroMarket.repositories.ProductRepository;
import com.PeaceOfMind.MicroMarket.repositories.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private VentaMapper ventaMapper;

    public List<VentaDTO> listarPorRangoFecha(LocalDate inicio, LocalDate fin) {
        return ventaRepository.findByFechaBetween(inicio, fin)
                .stream()
                .map(venta -> ventaMapper.toDTO(venta))
                .collect(Collectors.toList());
    }

    public List<VentaDTO> getAllVentas() {
        return ventaRepository.findAll().stream()
                .map(venta -> ventaMapper.toDTO(venta))
                .collect(Collectors.toList());
    }

    public VentaDTO getVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada", id.toString()));
        return ventaMapper.toDTO(venta);
    }

    public VentaDTO deleteVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada", id.toString()));
        ventaRepository.delete(venta);
        return ventaMapper.toDTO(venta);
    }

    public VentaDTO procesarVenta(VentaDTO request) {
        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        
        if (request.getEmpleadoId() != null) {
            Empleado empleado = empleadoRepository.findById(request.getEmpleadoId())
                .orElseThrow(() -> new NotFoundException("Empleado no existe", request.getEmpleadoId().toString()));
            venta.setEmpleado(empleado);
        } else {
            throw new BadRequestException("El ID del empleado es requerido");
        }
        
        double acumuladoSubtotal = 0;
        List<DetalleVenta> detallesList = new ArrayList<>();

        if (request.getDetalles() == null || request.getDetalles().isEmpty()) {
            throw new BadRequestException("La venta debe tener al menos un detalle");
        }

        for (DetalleVentaDTO item : request.getDetalles()) {
            Product product = productRepository.findById(item.getProductoId())
                .orElseThrow(() -> new NotFoundException("Producto no existe", item.getProductoId().toString()));

            if (product.getStock() < item.getCantidad()) {
                throw new BadRequestException("Stock insuficiente para: " + product.getNombre());
            }

            product.setStock(product.getStock() - item.getCantidad());
            productRepository.save(product);

            DetalleVenta detalle = new DetalleVenta();
            detalle.setProducto(product);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(product.getPrecio());
            
            double subtotalDetalle = detalle.getPrecioUnitario() * detalle.getCantidad();
            detalle.setSubtotal(subtotalDetalle);
            detalle.setVenta(venta);
            
            detallesList.add(detalle);
            acumuladoSubtotal += subtotalDetalle;
        }

        venta.setSubtotal(acumuladoSubtotal);
        venta.setIva(acumuladoSubtotal * 0.19); 
        venta.setTotal(venta.getSubtotal() + venta.getIva());
        venta.setDetalles(detallesList);

        return ventaMapper.toDTO(ventaRepository.save(venta));
    }
}