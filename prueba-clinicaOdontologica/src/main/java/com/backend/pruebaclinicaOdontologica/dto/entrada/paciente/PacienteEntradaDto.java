package com.backend.pruebaclinicaOdontologica.dto.entrada.paciente;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PacienteEntradaDto {

    @Size(max = 50, message = "El nombre del paciente debe tener hasta 50 caracteres")
    //capacidad de la columna de la tabla @Length
    @NotNull(message = "El nombre del paciente no puede ser null")
    @NotBlank(message = "Debe especificarse el nombre del paciente")
    private String nombre;

    @Size(max = 50, message = "El apellido del paciente debe tener hasta 50 caracteres")
    @NotNull(message = "El apellido del paciente no puede ser null")
    @NotBlank(message = "Debe especificarse el apellido del paciente")
    private String apellido;

    //@Pattern(regexp = "\\d{1,8}", message = "El campo dn1 debe tener como maximo 8 digitos")
    @NotNull(message = "El dni del paciente no puede ser null")
    //@NotBlank(message = "Debe especificarse el dni del paciente")
    @Digits(integer = 8, fraction = 0, message = "El número debe tener como máximo 8 dígitos")
    private int dni;

    @FutureOrPresent(message = "La fecha no puede ser anterior al dia de hoy")
    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    //para mapear el campo que esta escrtio mal @JsonProperty("fechaingreso")
    private LocalDate fechaIngreso;

    @NotNull(message = "El domicilio del paciente no puede ser null")
    @Valid
    private DomicilioEntradaDto domicilio;

    //dos constructores
    public PacienteEntradaDto() {}

    public PacienteEntradaDto(String nombre, String apellido, int dni, LocalDate fechaIngreso, DomicilioEntradaDto domicilio)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    // G Y S

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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioEntradaDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioEntradaDto domicilio) {
        this.domicilio = domicilio;
    }
}
