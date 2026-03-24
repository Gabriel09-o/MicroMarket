package com.PeaceOfMind.MicroMarket.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.PeaceOfMind.MicroMarket.dtos.ProductosProveedoresDTO;
import com.PeaceOfMind.MicroMarket.services.ProductosProveedoresServices;

@RestController
@RequestMapping("/productos-proveedores")
public class ProductosProveedoresController {

    @Autowired
    private ProductosProveedoresServices services;

    @GetMapping
    public List<ProductosProveedoresDTO> getAll() {
        return services.getAllAsociaciones();
    }

    @PostMapping
    public ProductosProveedoresDTO asociar(@RequestBody ProductosProveedoresDTO dto) {
        return services.saveAsociacion(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarAsociacion(@PathVariable Long id) {
        services.deleteAsociacion(id);
    }
}
