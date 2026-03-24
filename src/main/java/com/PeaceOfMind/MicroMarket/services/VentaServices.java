package com.PeaceOfMind.MicroMarket.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.dtos.DetalleVentaDTO;
import com.PeaceOfMind.MicroMarket.dtos.VentaDTO;
import com.PeaceOfMind.MicroMarket.dtos.VentaMapper;
import com.PeaceOfMind.MicroMarket.exceptions.BadRequestException;
import com.PeaceOfMind.MicroMarket.exceptions.NotFoundException;
import com.PeaceOfMind.MicroMarket.models.DetalleVenta;
import com.PeaceOfMind.MicroMarket.models.Product;
import com.PeaceOfMind.MicroMarket.models.Venta;
import com.PeaceOfMind.MicroMarket.repositories.ProductRepository;
import com.PeaceOfMind.MicroMarket.repositories.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VentaMapper ventaMapper;

    public List<VentaDTO> listarPorRangoFecha(LocalDate inicio, LocalDate fin) {
        return ventaRepository.findByFechaBetween(inicio, fin)
                .stream()
                .map(venta -> ventaMapper.toDTO(venta))
                .collect(Collectors.toList());
    }

    public VentaDTO procesarVenta(VentaDTO request) {
        Venta venta = new Venta();
        
        double acumuladoSubtotal = 0;

        for (DetalleVentaDTO item : request.getDetalleVentaList()) {
            Product product = productRepository.findById(item.getProduct().getId())
                .orElseThrow(() -> new NotFoundException("Producto no existe", null));

            if (product.getStock() < item.getCantidad()) {
                throw new BadRequestException("Stock insuficiente para: " + product.getNombre());
            }

            product.setStock(product.getStock() - item.getCantidad());
            productRepository.save(product);

            DetalleVenta detalle = new DetalleVenta();
            detalle.setProduct(product);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(product.getPrecio());
            
            double subtotalDetalle = detalle.getPrecioUnitario() * detalle.getCantidad();
            detalle.setSubtotal(subtotalDetalle);
            
            acumuladoSubtotal += subtotalDetalle;
        }

        venta.setSubtotal(acumuladoSubtotal);
        venta.setIva(acumuladoSubtotal * 0.19); 
        venta.setTotal(venta.getSubtotal() + venta.getIva());

        return ventaMapper.toDTO(ventaRepository.save(venta));
    }
}