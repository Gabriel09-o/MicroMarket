package com.PeaceOfMind.MicroMarket.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.dtos.DetalleVentaDTO;
import com.PeaceOfMind.MicroMarket.dtos.DetalleVentaMapper;
import com.PeaceOfMind.MicroMarket.models.DetalleVenta;
import com.PeaceOfMind.MicroMarket.models.Products; 
import com.PeaceOfMind.MicroMarket.repositories.DetalleVentaepository;
import com.PeaceOfMind.MicroMarket.repositories.ProductsRepository; 
import com.PeaceOfMind.MicroMarket.exceptions.NotFoundException;

@Service
public class DetalleVentaServices {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private DetalleVentaMapper detalleVentaMapper;

    public DetalleVentaDTO registrarDetalle(DetalleVentaDTO dto) {
        Products product = productsRepository.findById(dto.getProductId())
                .orElseThrow(() -> new NotFoundException("Producto", dto.getProductId().toString()));

        DetalleVenta detalle = detalleVentaMapper.toEntity(dto);
        detalle.setProduct(product); 

        detalle.setSubtotal(detalle.getCantidad() * detalle.getPrecioUnitario());

        return detalleVentaMapper.toDTO(detalleVentaRepository.save(detalle));
    }
}
