package com.PeaceOfMind.MicroMarket.services;

import java.util.List;
import com.PeaceOfMind.MicroMarket.entity.Category;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.dtos.CategoryDTO;
import com.PeaceOfMind.MicroMarket.dtos.ProductDTO;
import com.PeaceOfMind.MicroMarket.dtos.ProductsMapper;
import com.PeaceOfMind.MicroMarket.entity.Product;
import com.PeaceOfMind.MicroMarket.exceptions.NotFoundException;

import com.PeaceOfMind.MicroMarket.repositories.CategoryRepository;
import com.PeaceOfMind.MicroMarket.repositories.ProductRepository;

@Service
public class ProductsServices {

    @Autowired
    private ProductRepository productsRepository;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTO getProduct(Long idProducto) {

        Optional<Product> optionalProduct = productsRepository.findById(idProducto);

        if (optionalProduct.isEmpty()) {
            throw new NotFoundException("product", idProducto.toString());
        }

        Product product = optionalProduct.get();

        return productsMapper.toDTO(product, true);
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {

        CategoryDTO categoryDTO = productDTO.getCategory();

        if (categoryDTO == null) {
            throw new NotFoundException("Category NO ENCONTRADA", null);
        }

        Category category = categoryRepository.findById(categoryDTO.getId())
                .orElseThrow(() -> new NotFoundException("category", categoryDTO.getId().toString()));

        Product product = productsMapper.getProduct(productDTO);
        product.setCategoria(category);

        product.setEstado(true);

        productsRepository.save(product);

        return productsMapper.toDTO(product, true);
    }

    public ProductDTO updateProduct(Long idProducto, ProductDTO productDTO) {

        Product product = productsRepository.findById(idProducto)
                .orElseThrow(() -> new NotFoundException("Product NO ENCONTRADO con id ", idProducto.toString()));

        product.setNombre(productDTO.getNombre());
        product.setPrecio(productDTO.getPrecio());
        product.setStock(productDTO.getStock());

        product.setCodigoBarras(productDTO.getCodigoBarras());

        productsRepository.save(product);

        return productsMapper.toDTO(product, true);
    }

    public ProductDTO deleteProduct(Long idProducto) {

        Optional<Product> optionalProduct = productsRepository.findById(idProducto);

        if (optionalProduct.isEmpty()) {
            throw new NotFoundException("Product NO ENCONTRADO con id ", idProducto.toString());
        }

        Product product = optionalProduct.get();

        productsRepository.delete(product);

        return productsMapper.toDTO(product, true);
    }

    public List<ProductDTO> getAllProducts() {

        return productsRepository.findAll().stream()
                .map(product -> productsMapper.toDTO(product, true))
                .collect(Collectors.toList());

    }
}