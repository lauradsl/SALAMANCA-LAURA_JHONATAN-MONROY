package com.backend.pruebaclinicaOdontologica.service;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService
{
    List<OdontologoSalidaDto> listarOdontologos();

    OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo);

    OdontologoSalidaDto buscarOdontologoPorId(Long id);

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;

    OdontologoSalidaDto modificarOdontologo(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto);
}
