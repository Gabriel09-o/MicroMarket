package com.PeaceOfMind.MicroMarket.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PeaceOfMind.MicroMarket.dtos.DetalleVentaDTO;
import com.PeaceOfMind.MicroMarket.dtos.VentaDTO;
import com.PeaceOfMind.MicroMarket.entity.DetalleVenta;
import com.PeaceOfMind.MicroMarket.entity.Empleado;
import com.PeaceOfMind.MicroMarket.entity.Product;
import com.PeaceOfMind.MicroMarket.entity.Venta;
import com.PeaceOfMind.MicroMarket.repositories.EmpleadoRepository;
import com.PeaceOfMind.MicroMarket.repositories.ProductRepository;
import com.PeaceOfMind.MicroMarket.repositories.VentaRepository;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductRepository productRepository;
    private final EmpleadoRepository empleadoRepository;

    public VentaService(VentaRepository ventaRepository,
                        ProductRepository productRepository,
                        EmpleadoRepository empleadoRepository) {
        this.ventaRepository = ventaRepository;
        this.productRepository = productRepository;
        this.empleadoRepository = empleadoRepository;
    }

@Transactional
public Venta crearVenta(VentaDTO ventaDTO) {

    Empleado empleado = empleadoRepository.findById(ventaDTO.getEmpleadoId())
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

    Venta venta = new Venta();
    venta.setEmpleado(empleado);

    List<DetalleVenta> detalles = new ArrayList<>();
    BigDecimal subtotalGeneral = BigDecimal.ZERO;

    for (DetalleVentaDTO dto : ventaDTO.getDetalles()) {

        Product producto = productRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getStock() < dto.getCantidad()) {
            throw new RuntimeException("Stock insuficiente: " + producto.getNombre());
        }

        BigDecimal cantidad = BigDecimal.valueOf(dto.getCantidad());

        BigDecimal subtotal = producto.getPrecio().multiply(cantidad);

        DetalleVenta detalle = new DetalleVenta();
        detalle.setVenta(venta);
        detalle.setProducto(producto);
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(producto.getPrecio());
        detalle.setSubtotal(subtotal);

        producto.setStock(producto.getStock() - dto.getCantidad());
        productRepository.save(producto);

        subtotalGeneral = subtotalGeneral.add(subtotal);
        detalles.add(detalle);
    }

    BigDecimal iva = subtotalGeneral.multiply(BigDecimal.valueOf(0.19));
    BigDecimal total = subtotalGeneral.add(iva);

    venta.setSubtotal(subtotalGeneral);
    venta.setIva(iva);
    venta.setTotal(total);
    venta.setDetalles(detalles);

    return ventaRepository.save(venta);
}
}