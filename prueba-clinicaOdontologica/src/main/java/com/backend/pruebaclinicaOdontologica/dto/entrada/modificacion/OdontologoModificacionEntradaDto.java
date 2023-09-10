package com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OdontologoModificacionEntradaDto {
    @NotNull (message = "Debe ingresar un id")
    private Long id;

    @NotNull (message = "Debe ingresar una matricula")
    @NotBlank(message = "Debe especificar la matricula")
    private String matricula;

    @NotNull (message = "Debe ingresar un nombre")
    @NotBlank (message = "Debe especificar el nombre")
    private String nombre;

    @NotNull (message = "Debe ingresar un apellido")
    @NotBlank (message = "Debe especificar el apellido")
    private String apellido;


    public OdontologoModificacionEntradaDto() {
    }

    public OdontologoModificacionEntradaDto(Long id, String matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
