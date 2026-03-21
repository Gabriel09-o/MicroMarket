package com.PeaceOfMind.MicroMarket.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@SQLDelete(sql = "UPDATE Categories SET deleted = true WHERE id_categoria = ?")
@SQLRestriction("deleted = false")

@Entity
@Table(name = "Categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<Product> productos;

    @Column(nullable = false)
    private Boolean deleted = false;
}