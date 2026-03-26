package com.PeaceOfMind.MicroMarket.dtos;

import org.springframework.stereotype.Component;

import com.PeaceOfMind.MicroMarket.entity.Proveedor;

@Component
public class ProveedoresMapper {

    public ProveedorDTO toDTO(Proveedor proveedor) {
        if (proveedor == null) {
            return null;
        }

        ProveedorDTO dto = new ProveedorDTO();
        dto.setId(proveedor.getId());
        dto.setNombre(proveedor.getNombre());
        dto.setNit(proveedor.getNit());
        dto.setTelefono(proveedor.getTelefono());
        dto.setEmail(proveedor.getEmail());
        dto.setDireccion(proveedor.getDireccion());

        return dto;
    }

    public Proveedor getProveedor(ProveedorDTO dto) {
        if (dto == null) {
            return null;
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setId(dto.getId());
        proveedor.setNombre(dto.getNombre());
        proveedor.setNit(dto.getNit());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setEmail(dto.getEmail());
        proveedor.setDireccion(dto.getDireccion());

        return proveedor;
    }
}
