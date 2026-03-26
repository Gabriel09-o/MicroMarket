package com.PeaceOfMind.MicroMarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PeaceOfMind.MicroMarket.dtos.ProductDTO;
import com.PeaceOfMind.MicroMarket.services.ProductsServices;

@RestController
@RequestMapping("/productos")
public class ProductsController {

    @Autowired
    private ProductsServices productsServices;

    @GetMapping
    public List<ProductDTO> getAllProductos() {
        return productsServices.getAllProducts();
    }

    @GetMapping("/{idProducto}")
    public ProductDTO getProductos(@PathVariable Long idProducto) {
        return productsServices.getProduct(idProducto);
    }

    @PostMapping()
    public ProductDTO createProducto(@RequestBody ProductDTO productDTO) {

        return productsServices.saveProduct(productDTO);

    }

    @PutMapping("/{idProducto}")
    public ProductDTO updateProducto(@PathVariable Long idProducto, @RequestBody ProductDTO productDTO) {
        return productsServices.updateProduct(idProducto, productDTO);
    }

    @DeleteMapping("/{idProducto}")
    public ProductDTO deleteProducto(@PathVariable Long idProducto) {
        return productsServices.deleteProduct(idProducto);
    }
}
