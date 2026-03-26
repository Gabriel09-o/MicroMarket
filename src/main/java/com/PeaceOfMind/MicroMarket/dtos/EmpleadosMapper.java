package com.PeaceOfMind.MicroMarket.dtos;

import org.springframework.stereotype.Component;

import com.PeaceOfMind.MicroMarket.entity.Cargo;
import com.PeaceOfMind.MicroMarket.entity.Empleado;

@Component
public class EmpleadosMapper {

    public EmpleadoDTO toDTO(Empleado empleado) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(empleado.getId());
        dto.setNombre(empleado.getNombre());
        dto.setCedula(empleado.getCedula());
        if (empleado.getCargo() != null) {
            dto.setCargo(empleado.getCargo().name());
        }
        dto.setFechaIngreso(empleado.getFechaIngreso());
        dto.setSalario(empleado.getSalario());
        return dto;
    }

    public Empleado getEmpleado(EmpleadoDTO dto) {
        Empleado empleado = new Empleado();
        empleado.setId(dto.getId());
        empleado.setNombre(dto.getNombre());
        empleado.setCedula(dto.getCedula());
        if (dto.getCargo() != null) {
            empleado.setCargo(Cargo.valueOf(dto.getCargo()));
        }
        empleado.setFechaIngreso(dto.getFechaIngreso());
        empleado.setSalario(dto.getSalario());
        return empleado;
    }
    
}
