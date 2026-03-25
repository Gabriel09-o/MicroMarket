package com.PeaceOfMind.MicroMarket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PeaceOfMind.MicroMarket.entity.Product;
import com.PeaceOfMind.MicroMarket.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product crear(@RequestBody Product product) {
        return productService.crear(product);
    }

    @GetMapping
    public List<Product> listar() {
        return productService.listar();
    }

    @GetMapping("/{id}")
    public Product buscar(@PathVariable Long id) {
        return productService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Product actualizar(@PathVariable Long id, @RequestBody Product product) {
        return productService.actualizar(id, product);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productService.eliminar(id);
    }
}