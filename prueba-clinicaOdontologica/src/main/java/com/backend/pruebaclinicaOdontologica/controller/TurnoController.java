package com.backend.pruebaclinicaOdontologica.controller;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.pruebaclinicaOdontologica.exception.BadRequestException;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.pruebaclinicaOdontologica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController
{
    private final ITurnoService turnoService;

    @Autowired
    public TurnoController(ITurnoService turnoService)
    {
        this.turnoService = turnoService;
    }

    //POST REGISTRAR
    @PostMapping("/registrar")
    public ResponseEntity<TurnoSalidaDto> registrarTurno(@Valid @RequestBody TurnoEntradaDto turnoEntradaDto) throws BadRequestException
    {
        return new ResponseEntity<>(turnoService.registrarTurno(turnoEntradaDto), HttpStatus.CREATED);
    }

    //GET LISTAR TURNOS
    @GetMapping("/")
    public ResponseEntity<List<TurnoSalidaDto>> listarTurnos()
    {
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }

    //GET BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<TurnoSalidaDto> buscarTurnoPorId(@PathVariable Long id)
    {
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
    }

    //GET ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException
    {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.NO_CONTENT);
    }

    //PUT MODIFICAR
    @PutMapping("modificar")
    public ResponseEntity<TurnoSalidaDto> modificarTurno(@Valid @RequestBody TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException
    {
        return new ResponseEntity<>(turnoService.modificarTurno(turnoModificacionEntradaDto), HttpStatus.OK);
    }

}
