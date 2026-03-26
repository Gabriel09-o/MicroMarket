package com.PeaceOfMind.MicroMarket.dtos;

import java.time.LocalDate;

import com.PeaceOfMind.MicroMarket.entity.Cargo;

import lombok.Data;

@Data
public class EmpleadoDTO {

    private Long id;
    private String cedula;
    private String nombre;
    private String cargo;
    private LocalDate fechaIngreso;
    private double salario;

    public EmpleadoDTO() {}

    public EmpleadoDTO(Long id, String cedula, String nombre,
                       String cargo, LocalDate fechaIngreso, Double salario) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.cargo = cargo;
        this.fechaIngreso = fechaIngreso;
        this.salario = salario;
    }

}