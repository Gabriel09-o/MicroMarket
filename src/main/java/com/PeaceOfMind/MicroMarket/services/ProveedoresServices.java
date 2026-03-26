package com.PeaceOfMind.MicroMarket.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.dtos.ProductsMapper;
import com.PeaceOfMind.MicroMarket.dtos.ProveedoresMapper;
import com.PeaceOfMind.MicroMarket.dtos.ProveedorDTO;
import com.PeaceOfMind.MicroMarket.dtos.ProductDTO;

import com.PeaceOfMind.MicroMarket.entity.Proveedor;
import com.PeaceOfMind.MicroMarket.entity.Product;
import com.PeaceOfMind.MicroMarket.entity.ProductoProveedor;

import com.PeaceOfMind.MicroMarket.repositories.ProveedorRepository;
import com.PeaceOfMind.MicroMarket.repositories.ProductRepository;
import com.PeaceOfMind.MicroMarket.repositories.ProductosProveedoresRepository;

import com.PeaceOfMind.MicroMarket.exceptions.NotFoundException;

@Service
public class ProveedoresServices {

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ProveedoresMapper proveedoresMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProductosProveedoresRepository productosProveedoresRepository;

    public List<ProveedorDTO> getAllProveedores() {
        return proveedorRepository
                .findAll()
                .stream()
                .map(proveedor -> proveedoresMapper.toDTO(proveedor))
                .collect(Collectors.toList());
    }

    public ProveedorDTO saveProveedor(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = proveedoresMapper.getProveedor(proveedorDTO);
        proveedorRepository.save(proveedor);
        return proveedoresMapper.toDTO(proveedor);
    }

    public ProveedorDTO updateProveedor(Long idProveedor, ProveedorDTO proveedorDTO) {
        Proveedor proveedor = proveedorRepository.findById(idProveedor)
                .orElseThrow(() -> new NotFoundException("Proveedor NO ENCONTRADO con id ", idProveedor.toString()));

        proveedor.setNombre(proveedorDTO.getNombre());
        proveedor.setNit(proveedorDTO.getNit());
        proveedor.setTelefono(proveedorDTO.getTelefono());
        proveedor.setEmail(proveedorDTO.getEmail());
        proveedor.setDireccion(proveedorDTO.getDireccion());

        proveedorRepository.save(proveedor);

        return proveedoresMapper.toDTO(proveedor);
    }

    public ProveedorDTO deleteProveedor(Long idProveedor) {
        Optional<Proveedor> optionalProveedor = proveedorRepository.findById(idProveedor);

        if (optionalProveedor.isEmpty()) {
            throw new NotFoundException("Proveedor NO ENCONTRADO con id ", idProveedor.toString());
        }

        Proveedor proveedor = optionalProveedor.get();
        proveedorRepository.delete(proveedor);

        return proveedoresMapper.toDTO(proveedor);
    }

    public List<ProductDTO> getProductosByProveedor(Long idProveedor) {
        return productosProveedoresRepository
                .findByProveedorId(idProveedor)
                .stream()
                .map(rel -> productsMapper.toDTO(rel.getProducto(), true))
                .collect(Collectors.toList());
    }

    public ProductDTO entradaAlmacen(Long productId, Long proveedorId, Integer cantidad) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado", productId.toString()));

        Proveedor proveedor = proveedorRepository.findById(proveedorId)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado", proveedorId.toString()));

        int nuevoStock = (product.isEstado() ? product.getStock() : 0) + cantidad;
        product.setStock(nuevoStock);

        boolean exists = productosProveedoresRepository.findByProveedorId(proveedorId).stream()
                .anyMatch(rel -> rel.getProducto().getId().equals(productId));

        if (!exists) {
            ProductoProveedor rel = new ProductoProveedor();
            rel.setProducto(product);
            rel.setProveedor(proveedor);
            productosProveedoresRepository.save(rel);
        }

        productRepository.save(product);

        return productsMapper.toDTO(product, true);
    }
}
