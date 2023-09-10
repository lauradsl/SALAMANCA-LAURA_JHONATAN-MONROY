package com.backend.pruebaclinicaOdontologica.service;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.pruebaclinicaOdontologica.exception.BadRequestException;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService
{
    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException;
    List<TurnoSalidaDto> listarTurnos();
    TurnoSalidaDto buscarTurnoPorId(Long id);
    void eliminarTurno(Long id) throws ResourceNotFoundException;

    TurnoSalidaDto modificarTurno(TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException;
}
