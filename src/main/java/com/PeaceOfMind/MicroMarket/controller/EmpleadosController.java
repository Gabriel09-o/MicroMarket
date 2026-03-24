package com.PeaceOfMind.MicroMarket.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.PeaceOfMind.MicroMarket.dtos.EmpleadosDTO;
import com.PeaceOfMind.MicroMarket.models.Cargo;
import com.PeaceOfMind.MicroMarket.services.EmpleadosServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empleados")
public class EmpleadosController {

    @Autowired
    private EmpleadosServices empleadosServices;

    @GetMapping("/cargo/{cargo}")
    public ResponseEntity<List<EmpleadosDTO>> listarPorCargo(
            @PathVariable Cargo cargo) {
        return ResponseEntity.ok(empleadosServices.listarPorCargo(cargo));
    }

    @GetMapping("/fecha-ingreso")
    public ResponseEntity<List<EmpleadosDTO>> listarPorRangoFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(empleadosServices.listarPorRangoFecha(inicio, fin));
    }

    @PostMapping
    public ResponseEntity<EmpleadosDTO> crearEmpleado(@Valid @RequestBody EmpleadosDTO empleadoDTO) {

        EmpleadosDTO empleadoDTO1 = empleadosServices.saveEmpleado(empleadoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cedula}")
                .buildAndExpand(empleadoDTO1.getCedula())
                .toUri();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, uri.toString())
                .body(empleadoDTO1);
    }
}
