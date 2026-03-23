package com.PeaceOfMind.MicroMarket.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PeaceOfMind.MicroMarket.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByCedula(String cedula);

}