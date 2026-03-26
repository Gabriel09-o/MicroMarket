package com.PeaceOfMind.MicroMarket.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.PeaceOfMind.MicroMarket.dtos.ProveedorDTO;
import com.PeaceOfMind.MicroMarket.dtos.ProductDTO;
import com.PeaceOfMind.MicroMarket.services.ProveedoresServices;

@RestController
@RequestMapping("/proveedores")
public class ProveedoresController {

    @Autowired
    private ProveedoresServices proveedoresServices;

    @GetMapping
    public List<ProveedorDTO> getAll() {
        return proveedoresServices.getAllProveedores();
    }

    @PostMapping
    public ProveedorDTO create(@RequestBody ProveedorDTO proveedorDTO) {
        return proveedoresServices.saveProveedor(proveedorDTO);
    }

    @PutMapping("/{id}")
    public ProveedorDTO update(@PathVariable Long id, @RequestBody ProveedorDTO proveedorDTO) {
        return proveedoresServices.updateProveedor(id, proveedorDTO);
    }

    @DeleteMapping("/{id}")
    public ProveedorDTO delete(@PathVariable Long id) {
        return proveedoresServices.deleteProveedor(id);
    }

    @GetMapping("/{id}/productos")
    public List<ProductDTO> getProductos(@PathVariable Long id) {
        return proveedoresServices.getProductosByProveedor(id);
    }
}
