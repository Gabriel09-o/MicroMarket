package com.PeaceOfMind.MicroMarket.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.dtos.ProductosProveedoresDTO;
import com.PeaceOfMind.MicroMarket.dtos.ProductosProveedoresMapper;
import com.PeaceOfMind.MicroMarket.models.ProductosProveedores;
import com.PeaceOfMind.MicroMarket.repositories.ProductosProveedoresRepository;

@Service
public class ProductosProveedoresServices {

    @Autowired
    private ProductosProveedoresRepository repository;

    @Autowired
    private ProductosProveedoresMapper mapper;

    public List<ProductosProveedoresDTO> getAll() {

        return repository.findAll().stream()
                .map(relacion -> mapper.toDTO(relacion))
                .collect(Collectors.toList());
    }

    public ProductosProveedoresDTO save(ProductosProveedoresDTO dto) {

        Productos_Proveedores relacion = mapper.toEntity(dto);
        repository.save(relacion);

        return mapper.toDTO(relacion);
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
}