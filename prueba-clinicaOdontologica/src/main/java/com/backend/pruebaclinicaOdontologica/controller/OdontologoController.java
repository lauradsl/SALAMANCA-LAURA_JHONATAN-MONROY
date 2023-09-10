package com.backend.pruebaclinicaOdontologica.controller;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.pruebaclinicaOdontologica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController
{
    private final IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(IOdontologoService odontologoService)
    {
        this.odontologoService = odontologoService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("registrar")
    public ResponseEntity<OdontologoSalidaDto> registarOdontologo(@Valid @RequestBody OdontologoEntradaDto odontologo)
    {
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologo), HttpStatus.CREATED);
    }

    @PutMapping("modificar")
    public ResponseEntity<OdontologoSalidaDto> modificarOdontologo(@Valid @RequestBody OdontologoModificacionEntradaDto odontologo)
    {
        return new ResponseEntity<>(odontologoService.modificarOdontologo(odontologo),HttpStatus.OK);
    }

    //SWAGGER

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorId(@PathVariable Long id)
    {
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("Odontologo eliminado correctamente", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/")
    public ResponseEntity<List<OdontologoSalidaDto>> listarTodos()
    {
        return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);
    }

}
