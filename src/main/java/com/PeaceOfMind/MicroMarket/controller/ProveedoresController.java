package com.PeaceOfMind.MicroMarket.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.PeaceOfMind.MicroMarket.dtos.ProveedoresDTO;
import com.PeaceOfMind.MicroMarket.dtos.ProductsDTO;
import com.PeaceOfMind.MicroMarket.services.ProveedoresServices;

@RestController
@RequestMapping("/proveedores")
public class ProveedoresController {

    @Autowired
    private ProveedoresServices proveedoresServices;

    @GetMapping
    public List<ProveedoresDTO> getAll() {
        return proveedoresServices.getAllProveedores();
    }

    @PostMapping
    public ProveedoresDTO create(@RequestBody ProveedoresDTO proveedoresDTO) {
        return proveedoresServices.saveProveedor(proveedoresDTO);
    }

    @PutMapping("/{id}")
    public ProveedoresDTO update(@PathVariable Long id, @RequestBody ProveedoresDTO proveedoresDTO) {
        return proveedoresServices.updateProveedor(id, proveedoresDTO);
    }

    @DeleteMapping("/{id}")
    public ProveedoresDTO delete(@PathVariable Long id) {
        return proveedoresServices.deleteProveedor(id);
    }

    @GetMapping("/{id}/productos")
    public List<ProductsDTO> getProductos(@PathVariable Long id) {
        return proveedoresServices.getProductosByProveedor(id);
    }
}
