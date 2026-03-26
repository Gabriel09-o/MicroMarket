package com.PeaceOfMind.MicroMarket.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.PeaceOfMind.MicroMarket.entity.Cargo;
import com.PeaceOfMind.MicroMarket.entity.Empleado;

import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByCedula(String cedula);

    // Cambia findByCargos por findByCargo
    @Query("SELECT e FROM Empleado e WHERE e.cargo = :cargo")
    List<Empleado> findByCargos(Cargo cargo);

    List<Empleado> findByFechaIngresoBetween(LocalDate inicio, LocalDate fin);

    Empleado findByCargo(Cargo cargo);

}