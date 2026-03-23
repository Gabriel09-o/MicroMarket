package com.PeaceOfMind.MicroMarket.dtos;

import java.time.LocalDate;

public class EmpleadoDTO {

    private Long id;
    private String cedula;
    private String nombre;
    private String cargo;
    private LocalDate fechaIngreso;
    private Double salario;

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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }
}