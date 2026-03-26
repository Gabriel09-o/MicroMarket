package com.PeaceOfMind.MicroMarket.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.dtos.DetalleVentaDTO;
import com.PeaceOfMind.MicroMarket.dtos.DetalleVentaMapper;
import com.PeaceOfMind.MicroMarket.entity.DetalleVenta;
import com.PeaceOfMind.MicroMarket.entity.Product;
import com.PeaceOfMind.MicroMarket.exceptions.NotFoundException;
import com.PeaceOfMind.MicroMarket.repositories.DetalleVentaRepository;
import com.PeaceOfMind.MicroMarket.repositories.ProductRepository;

@Service
public class DetalleVentaServices {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private ProductRepository productsRepository;

    @Autowired
    private DetalleVentaMapper detalleVentaMapper;

    public DetalleVentaDTO registrarDetalle(DetalleVentaDTO dto) {
        Product product = productsRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new NotFoundException("Producto", dto.getProductoId().toString()));

        DetalleVenta detalle = detalleVentaMapper.toEntity(dto);
        detalle.setProducto(product);

        detalle.setSubtotal(detalle.getCantidad() * detalle.getPrecioUnitario());

        return detalleVentaMapper.toDTO(detalleVentaRepository.save(detalle));
    }
}
