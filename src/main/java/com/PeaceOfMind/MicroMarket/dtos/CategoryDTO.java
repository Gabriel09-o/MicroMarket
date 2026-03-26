package com.PeaceOfMind.MicroMarket.dtos;

import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;
    private String nombre;
    private String descripcion;

    public CategoryDTO() {}

    public CategoryDTO(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
 
}