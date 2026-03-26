package com.PeaceOfMind.MicroMarket.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.dtos.EmpleadoDTO;
import com.PeaceOfMind.MicroMarket.dtos.EmpleadosMapper;
import com.PeaceOfMind.MicroMarket.entity.Cargo;
import com.PeaceOfMind.MicroMarket.entity.Empleado;
import com.PeaceOfMind.MicroMarket.repositories.EmpleadoRepository;

@Service
public class EmpleadosServices {

    @Autowired
    private EmpleadoRepository empleadosRepository;

    @Autowired
    private EmpleadosMapper empleadosMapper;

    public List<EmpleadoDTO> listarPorCargo(Cargo cargo) {

        return empleadosRepository.findByCargos(cargo)
                .stream()
                .map(empleado -> empleadosMapper.toDTO(empleado))
                .collect(Collectors.toList());
    }

    public List<EmpleadoDTO> listarPorRangoFecha(LocalDate inicio, LocalDate fin) {
        return empleadosRepository.findByFechaIngresoBetween(inicio, fin)
                .stream()
                .map(empleado -> empleadosMapper.toDTO(empleado))
                .collect(Collectors.toList());
    }

    public EmpleadoDTO saveEmpleado(EmpleadoDTO empleadoDTO) {

        if (empleadosRepository.findByCedula(empleadoDTO.getCedula()) != null) {
            throw new RuntimeException("El empleado ya existe");
        }

        Empleado empleado = empleadosMapper.getEmpleado(empleadoDTO);
        return empleadosMapper.toDTO(empleadosRepository.save(empleado));
    }
}
