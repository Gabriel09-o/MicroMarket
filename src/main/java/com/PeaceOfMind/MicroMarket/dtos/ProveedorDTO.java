package com.PeaceOfMind.MicroMarket.dtos;

import lombok.Data;

@Data
public class ProveedorDTO {

    private Long id;
    private String nit;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;

    public ProveedorDTO() {
    }

    public ProveedorDTO(Long id, String nit, String nombre, String telefono, String email, String direccion) {
        this.id = id;
        this.nit = nit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }
}