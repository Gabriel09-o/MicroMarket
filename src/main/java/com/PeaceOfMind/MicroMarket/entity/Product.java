package com.PeaceOfMind.MicroMarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@SQLDelete(sql = "UPDATE Products SET deleted = true WHERE id_producto = ?")
@SQLRestriction("deleted = false")

@Entity
@Table(name = "Products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "codigo_barras", unique = true, nullable = false, length = 100)
    private String codigoBarras;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int stock;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category categoria;

    @Column(nullable = false)
    private boolean deleted = false;
}