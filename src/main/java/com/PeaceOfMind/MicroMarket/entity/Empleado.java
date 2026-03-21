package com.PeaceOfMind.MicroMarket.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@SQLDelete(sql = "UPDATE Empleados SET deleted = true WHERE id_empleado = ?")
@SQLRestriction("deleted = false")

@Entity
@Table(name = "Empleados")
@Data
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String cedula;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false)
    private Cargo cargo;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double salario;

    @Column(nullable = false)
    private Boolean deleted = false;
}