package com.PeaceOfMind.MicroMarket.dtos;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String nombre;
    private String codigoBarras;
    private double precio;
    private int stock;
    private boolean estado;
    private CategoryDTO category;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String nombre, String codigoBarras,
            Double precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.codigoBarras = codigoBarras;
        this.precio = precio;
        this.stock = stock;
    }

}