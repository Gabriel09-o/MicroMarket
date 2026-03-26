package com.PeaceOfMind.MicroMarket.dtos;

import org.springframework.stereotype.Component;

import com.PeaceOfMind.MicroMarket.entity.DetalleVenta;

@Component
public class DetalleVentaMapper {

    public DetalleVentaDTO toDTO(DetalleVenta detalle) {
        DetalleVentaDTO dto = new DetalleVentaDTO();
        dto.setId(detalle.getId());
        
        if (detalle.getProducto() != null) {
            dto.setProductoId(detalle.getProducto().getId());
        }
        
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        dto.setSubtotal(detalle.getSubtotal());
        return dto;
    }

    public DetalleVenta toEntity(DetalleVentaDTO dto) {
        DetalleVenta detalle = new DetalleVenta();
        detalle.setId(dto.getId());
        
        if (dto.getProductoId() != null) {
            com.PeaceOfMind.MicroMarket.entity.Product product = new com.PeaceOfMind.MicroMarket.entity.Product();
            product.setId(dto.getProductoId());
            detalle.setProducto(product);
        }
        
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        detalle.setSubtotal(dto.getSubtotal());
        return detalle;
    }

}
