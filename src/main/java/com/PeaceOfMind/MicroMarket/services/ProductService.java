package com.PeaceOfMind.MicroMarket.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.entity.Product;
import com.PeaceOfMind.MicroMarket.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product crear(Product product) {
        return productRepository.save(product);
    }

    public List<Product> listar() {
        return productRepository.findAll();
    }

    public Product buscarPorId(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Product actualizar(Long id, Product nuevo) {
        Product producto = buscarPorId(id);

        producto.setNombre(nuevo.getNombre());
        producto.setCodigoBarras(nuevo.getCodigoBarras());
        producto.setPrecio(nuevo.getPrecio());
        producto.setStock(nuevo.getStock());
        producto.setEstado(nuevo.getEstado());
        producto.setCategoria(nuevo.getCategoria());

        return productRepository.save(producto);
    }

    public void eliminar(Long id) {
        productRepository.deleteById(id);
    }
}