package com.PeaceOfMind.MicroMarket.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.dtos.CategoryDTO;
import com.PeaceOfMind.MicroMarket.dtos.ProductsDTO; 
import com.PeaceOfMind.MicroMarket.dtos.ProductsMapper; 
import com.PeaceOfMind.MicroMarket.exceptions.NotFoundException;
import com.PeaceOfMind.MicroMarket.models.Category;
import com.PeaceOfMind.MicroMarket.models.Products; 
import com.PeaceOfMind.MicroMarket.repositories.CategoryRepository;
import com.PeaceOfMind.MicroMarket.repositories.ProductsRepository; 

@Service
public class ProductsServices { 

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductsDTO getProduct(Long idProducto) {

        Optional<Products> optionalProduct = productsRepository.findById(idProducto);

        if (optionalProduct.isEmpty()) {
            throw new NotFoundException("product", idProducto.toString());
        }

        Products product = optionalProduct.get();

        return productsMapper.toDTO(product, true);
    }

    public ProductsDTO saveProduct(ProductsDTO productDTO) {

        CategoryDTO categoryDTO = productDTO.getCategory();

        if (categoryDTO == null) {
            throw new NotFoundException("Category NO ENCONTRADA", null);
        }

        Category category = categoryRepository.findById(categoryDTO.getId())
                .orElseThrow(() -> new NotFoundException("category", categoryDTO.getId().toString()));

        Products product = productsMapper.getProduct(productDTO, category);
        
        product.setEstado(true);
        
        productsRepository.save(product);

        return productsMapper.toDTO(product, true);
    }

    public ProductsDTO updateProduct(Long idProducto, ProductsDTO productDTO) {

        Products product = productsRepository.findById(idProducto)
                .orElseThrow(() -> new NotFoundException("Product NO ENCONTRADO con id ", idProducto.toString()));

        product.setNombre(productDTO.getNombre());
        product.setDescripcion(productDTO.getDescripcion());
        product.setPrecio(productDTO.getPrecio());
        product.setStock(productDTO.getStock());
        
        product.setCodigoBarras(productDTO.getCodigoBarras());

        productsRepository.save(product);

        return productsMapper.toDTO(product, true);
    }

    public ProductsDTO deleteProduct(Long idProducto) {

        Optional<Products> optionalProduct = productsRepository.findById(idProducto);

        if (optionalProduct.isEmpty()) {
            throw new NotFoundException("Product NO ENCONTRADO con id ", idProducto.toString());
        }

        Products product = optionalProduct.get();

        productsRepository.delete(product);

        return productsMapper.toDTO(product, true);
    }

    public List<ProductsDTO> getAllProducts() {

        return productsRepository.findAll().stream()
                .map(product -> productsMapper.toDTO(product, true))
                .collect(Collectors.toList());

    }
}