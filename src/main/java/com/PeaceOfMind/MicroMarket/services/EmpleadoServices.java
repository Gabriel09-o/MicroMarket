package com.PeaceOfMind.MicroMarket.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.dtos.EmpleadosDTO;
import com.PeaceOfMind.MicroMarket.dtos.EmpleadosMapper;
import com.PeaceOfMind.MicroMarket.models.Cargo;
import com.PeaceOfMind.MicroMarket.models.Empleados;
import com.PeaceOfMind.MicroMarket.repositories.EmpleadosRepository;

@Service
public class EmpleadosServices {

    @Autowired
    private EmpleadosRepository empleadosRepository;

    @Autowired
    private EmpleadosMapper empleadosMapper;

    public List<EmpleadosDTO> listarPorCargo(Cargo cargo) {
        
        return empleadosRepository.findByCargo(cargo)
                .stream()
                .map(empleado -> empleadosMapper.toDTO(empleado))
                .collect(Collectors.toList());
    }

    public List<EmpleadosDTO> listarPorRangoFecha(LocalDate inicio, LocalDate fin) {
        return empleadosRepository.findByFechaIngresoBetween(inicio, fin)
                .stream()
                .map(empleado -> empleadosMapper.toDTO(empleado))
                .collect(Collectors.toList());
    }

    public EmpleadosDTO saveEmpleado(EmpleadosDTO empleadoDTO) {

        if (empleadosRepository.findByCedula(empleadoDTO.getCedula()) != null) {
            throw new RuntimeException("El empleado ya existe");
        }

        Empleados empleado = empleadosMapper.getEmpleado(empleadoDTO);
        return empleadosMapper.toDTO(empleadosRepository.save(empleado));
    }
}
