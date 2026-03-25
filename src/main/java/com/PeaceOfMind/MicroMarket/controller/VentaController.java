package com.PeaceOfMind.MicroMarket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PeaceOfMind.MicroMarket.dtos.VentaDTO;
import com.PeaceOfMind.MicroMarket.entity.Venta;
import com.PeaceOfMind.MicroMarket.services.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public Venta crear(@RequestBody VentaDTO ventaDTO) {
        return ventaService.crearVenta(ventaDTO);
    }
    @GetMapping("/test")
public String test() {
    return "FUNCIONA";
}
}