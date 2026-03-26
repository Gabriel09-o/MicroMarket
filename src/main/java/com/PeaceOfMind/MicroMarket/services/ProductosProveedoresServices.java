package com.PeaceOfMind.MicroMarket.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import com.PeaceOfMind.MicroMarket.dtos.ProductosProveedoresDTO;
import com.PeaceOfMind.MicroMarket.dtos.ProductosProveedoresMapper;
import com.PeaceOfMind.MicroMarket.entity.ProductoProveedor;
import com.PeaceOfMind.MicroMarket.repositories.ProductosProveedoresRepository;
import org.springframework.stereotype.Service;

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

        ProductoProveedor relacion = mapper.gProductoProveedor(dto);

        repository.save(relacion);

        return mapper.toDTO(relacion);
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
}