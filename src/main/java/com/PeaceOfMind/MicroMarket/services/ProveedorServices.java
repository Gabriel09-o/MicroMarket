package com.PeaceOfMind.MicroMarket.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.dtos.ProductsDTO;
import com.PeaceOfMind.MicroMarket.dtos.ProductsMapper;
import com.PeaceOfMind.MicroMarket.dtos.ProveedoresDTO; 
import com.PeaceOfMind.MicroMarket.dtos.ProveedoresMapper; 
import com.PeaceOfMind.MicroMarket.exceptions.NotFoundException;
import com.PeaceOfMind.MicroMarket.models.Products;
import com.PeaceOfMind.MicroMarket.models.Proveedores; 
import com.PeaceOfMind.MicroMarket.repositories.ProductsRepository;
import com.PeaceOfMind.MicroMarket.repositories.ProveedoresRepository;  

@Service
public class ProveedoresServices { 

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ProveedoresMapper proveedoresMapper;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProveedoresRepository proveedoresRepository;

    public List<ProveedoresDTO> getAllProveedores() {

        return proveedoresRepository
                .findAll()
                .stream()
                .map(proveedor -> proveedoresMapper.toDTO(proveedor))
                .collect(Collectors.toList());

    }

    public ProveedoresDTO saveProveedor(ProveedoresDTO proveedoresDTO) {

        Proveedores proveedor = proveedoresMapper.getProveedor(proveedoresDTO);
        proveedoresRepository.save(proveedor);

        return proveedoresMapper.toDTO(proveedor);
    }

    public ProveedoresDTO updateProveedor(Long idProveedor, ProveedoresDTO proveedoresDTO) {

        Proveedores proveedor = proveedoresRepository.findById(idProveedor)
                .orElseThrow(() -> new NotFoundException("Proveedor NO ENCONTRADO con id ", idProveedor.toString()));

        proveedor.setNombre(proveedoresDTO.getNombre());
        proveedor.setNit(proveedoresDTO.getNit()); 
        proveedor.setTelefono(proveedoresDTO.getTelefono());
        proveedor.setEmail(proveedoresDTO.getEmail()); 
        proveedor.setDireccion(proveedoresDTO.getDireccion());

        proveedoresRepository.save(proveedor);

        return proveedoresMapper.toDTO(proveedor);
    }

    public ProveedoresDTO deleteProveedor(Long idProveedor) {

        Optional<Proveedores> optionalProveedor = proveedoresRepository.findById(idProveedor);

        if (optionalProveedor.isEmpty()) {
            throw new NotFoundException("Proveedor NO ENCONTRADO con id ", idProveedor.toString());
        }

        Proveedores proveedor = optionalProveedor.get();

        proveedoresRepository.delete(proveedor);

        return proveedoresMapper.toDTO(proveedor);
    }

    public List<ProductsDTO> getProductosByProveedor(Long idProveedor) {
        
        return productsRepository
                .findByProveedores_Id(idProveedor) 
                .stream()
                .map(product -> productsMapper.toDTO(product, true))
                .collect(Collectors.toList());
    }

    public ProductsDTO entradaAlmacen(Long productId, Long proveedorId, Integer cantidad) {
        
        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado", productId.toString()));

        Proveedores proveedor = proveedoresRepository.findById(proveedorId)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado", proveedorId.toString()));

        Integer nuevoStock = (product.getStock() != null ? product.getStock() : 0) + cantidad;
        product.setStock(nuevoStock);

        if (!product.getProveedoresList().contains(proveedor)) {
            product.getProveedoresList().add(proveedor);
        }

        productsRepository.save(product);

        return productsMapper.toDTO(product, true);
    }
}