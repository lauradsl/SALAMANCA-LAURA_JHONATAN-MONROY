package com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteModificacionEntradaDto {

    @NotNull (message = "Debe ingresar un id")
    private Long id;

    @NotNull (message = "Debe ingresar un nombre")
    @NotBlank(message = "Debe especificar el nombre")
    private String nombre;

    @NotNull (message = "Debe ingresar un apellido")
    @NotBlank (message = "Debe especificar el apellido")
    private String apellido;

    @NotNull (message = "Debe ingresar un dni")
    @NotBlank (message = "Debe especificar el dni")
    private String dni;

    @NotNull (message = "Debe ingresar una fecha de ingreso")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaIngreso;

    @NotNull
    @Valid
    private DomicilioModificacionEntradaDto domicilio;

    public PacienteModificacionEntradaDto() {
    }

    public PacienteModificacionEntradaDto(Long id, String nombre, String apellido, String dni, LocalDate fechaIngreso, DomicilioModificacionEntradaDto domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioModificacionEntradaDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioModificacionEntradaDto domicilio) {
        this.domicilio = domicilio;
    }
}
