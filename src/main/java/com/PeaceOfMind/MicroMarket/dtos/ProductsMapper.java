package com.PeaceOfMind.MicroMarket.dtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.PeaceOfMind.MicroMarket.entity.Product;

@Component
public class ProductsMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    public ProductDTO toDTO(Product product, boolean includeCategory) {

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setNombre(product.getNombre());
        dto.setPrecio(product.getPrecio());
        dto.setStock(product.getStock());
        dto.setCodigoBarras(product.getCodigoBarras());
        dto.setEstado(product.isEstado());

        if (includeCategory && product.getCategoria() != null) {

            dto.setCategory(categoryMapper.toDTO(product.getCategoria()));
        }

        return dto;
    }

    public Product getProduct(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setNombre(dto.getNombre());
        product.setPrecio(dto.getPrecio());
        product.setStock(dto.getStock());
        product.setCodigoBarras(dto.getCodigoBarras());
        
        product.setEstado(dto.isEstado());
        
        if (dto.getCategory() != null) {
            product.setCategoria(categoryMapper.getCategory(dto.getCategory()));
        }
        
        return product;
    }

}
