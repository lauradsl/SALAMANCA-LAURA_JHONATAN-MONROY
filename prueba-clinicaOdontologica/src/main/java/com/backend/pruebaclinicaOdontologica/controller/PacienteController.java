package com.backend.pruebaclinicaOdontologica.controller;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.pruebaclinicaOdontologica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController
{
    private final IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService)
    {
        this.pacienteService = pacienteService;
    }

    //POST
    //Definir el metodo. Definir nombre indicativo de endpoint
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("registrar")
    public ResponseEntity<PacienteSalidaDto> registarPaciente(@Valid @RequestBody PacienteEntradaDto paciente)
    {
        return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
    }

    //PUT
    @PutMapping("modificar")
    public ResponseEntity<PacienteSalidaDto> modificarPaciente(@Valid @RequestBody PacienteModificacionEntradaDto paciente)
    {
        return new ResponseEntity<>(pacienteService.modificarPaciente(paciente),HttpStatus.OK);
    }

    //GET
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<PacienteSalidaDto> buscarPacientePorId(@PathVariable Long id)
    {
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }

    //GET LISTAR TODOS
    @GetMapping("/")
    public ResponseEntity<List<PacienteSalidaDto>> listarTodos()
    {
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }
}
