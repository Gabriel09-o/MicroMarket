package com.PeaceOfMind.MicroMarket.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.entity.Empleado;
import com.PeaceOfMind.MicroMarket.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado crear(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public List<Empleado> listar() {
        return empleadoRepository.findAll();
    }

    public Empleado buscarPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    public Empleado actualizar(Long id, Empleado nuevo) {
        Empleado empleado = buscarPorId(id);

        empleado.setCedula(nuevo.getCedula());
        empleado.setNombre(nuevo.getNombre());
        empleado.setCargo(nuevo.getCargo());
        empleado.setFechaIngreso(nuevo.getFechaIngreso());
        empleado.setSalario(nuevo.getSalario());

        return empleadoRepository.save(empleado);
    }

    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }
}