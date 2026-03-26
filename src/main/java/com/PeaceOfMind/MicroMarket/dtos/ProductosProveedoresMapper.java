package com.PeaceOfMind.MicroMarket.dtos;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.PeaceOfMind.MicroMarket.entity.ProductoProveedor;
import com.PeaceOfMind.MicroMarket.entity.Product;
import com.PeaceOfMind.MicroMarket.entity.Proveedor;
import com.PeaceOfMind.MicroMarket.repositories.ProductRepository;
import com.PeaceOfMind.MicroMarket.repositories.ProveedorRepository;

@Component
public class ProductosProveedoresMapper {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    public ProductosProveedoresDTO toDTO(ProductoProveedor entity) {
        if (entity == null) {
            return null;
        }

        ProductosProveedoresDTO dto = new ProductosProveedoresDTO();
        dto.setId(entity.getId());

        if (entity.getProducto() != null) {
            dto.setProductoId(entity.getProducto().getId());
        }

        if (entity.getProveedor() != null) {
            dto.setProveedorId(entity.getProveedor().getId());
        }

        return dto;
    }

    public ProductoProveedor gProductoProveedor(ProductosProveedoresDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductoProveedor entity = new ProductoProveedor();
        entity.setId(dto.getId());

        if (dto.getProductoId() != null) {
            Product product = productRepository.findById(dto.getProductoId()).orElse(null);
            entity.setProducto(product);
        }

        if (dto.getProveedorId() != null) {
            Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId()).orElse(null);
            entity.setProveedor(proveedor);
        }

        return entity;
    }
}
