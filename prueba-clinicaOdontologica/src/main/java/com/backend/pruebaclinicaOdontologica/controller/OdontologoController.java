package com.backend.pruebaclinicaOdontologica.controller;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.pruebaclinicaOdontologica.service.IOdontologoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/odontologos")
public class OdontologoController
{
    private final IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(IOdontologoService odontologoService)
    {
        this.odontologoService = odontologoService;
    }

    //REGISTRAR
    //@CrossOrigin(origins = "http://127.0.0.1:5500")
    @Operation(summary = "Registro de un nuevo odontólogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Odontólogo guardado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("registrar")
    public ResponseEntity<OdontologoSalidaDto> registarOdontologo(@Valid @RequestBody OdontologoEntradaDto odontologo)
    {
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologo), HttpStatus.CREATED);
    }

    //MODIFICAR
    @Operation(summary = "Modificacion de un odontologo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo actualizado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "UServer error",
                    content = @Content)
    })
    @PutMapping("modificar")
    public ResponseEntity<OdontologoSalidaDto> modificarOdontologo(@Valid @RequestBody OdontologoModificacionEntradaDto odontologo)
    {
        return new ResponseEntity<>(odontologoService.modificarOdontologo(odontologo),HttpStatus.OK);
    }

    //BUSCAR
    @Operation(summary = "Búsqueda de un odontólogo por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorId(@PathVariable Long id)
    {
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    }

    //ELIMINAR
    @Operation(summary = "Eliminación de un odontologo por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Odontólogo eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("Odontologo eliminado correctamente", HttpStatus.NO_CONTENT);
    }

    //LISTAR
    @Operation(summary = "Listado de todos los odontólogos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de odontólogos obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<OdontologoSalidaDto>> listarTodos()
    {
        return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);
    }

}
