package com.PeaceOfMind.MicroMarket.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.PeaceOfMind.MicroMarket.dtos.VentaDTO;
import com.PeaceOfMind.MicroMarket.services.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentasController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<VentaDTO> getAll() {
        return ventaService.getAllVentas();
    }

    @PostMapping
    public VentaDTO create(@RequestBody VentaDTO ventaDTO) {
        return ventaService.saveVenta(ventaDTO);
    }

    @GetMapping("/{id}")
    public VentaDTO getById(@PathVariable Long id) {
        return ventaService.getVenta(id);
    }

    @DeleteMapping("/{id}")
    public VentaDTO delete(@PathVariable Long id) {
        return ventaService.deleteVenta(id);
    }
}
