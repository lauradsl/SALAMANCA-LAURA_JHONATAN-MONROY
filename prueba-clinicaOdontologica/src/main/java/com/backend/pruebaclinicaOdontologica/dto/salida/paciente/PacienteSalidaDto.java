package com.backend.pruebaclinicaOdontologica.dto.salida.paciente;

import com.backend.pruebaclinicaOdontologica.entity.Domicilio;

import java.time.LocalDate;

public class PacienteSalidaDto
{
    //Sale de la BD con un ID. No hay que validarlo. Sale como un Entity. Hay que transformalo en Service
    //Se modela de acuerdo a la necesidad de lo queremos retornar
    private Long id;
    private String nombre;
    private String apellido;
    private int dni;
    private LocalDate fechaIngreso;
    private DomicilioSalidaDto domicilio;

    //Dos constructores
    public PacienteSalidaDto() {};

    public PacienteSalidaDto(Long id, String nombre, String apellido, int dni, LocalDate fechaIngreso, DomicilioSalidaDto domicilio)
    {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    //G Y S
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

    public DomicilioSalidaDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioSalidaDto domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Apellido: " + apellido + " - DNI: " + dni + " - Fechas de ingreso: " + fechaIngreso + " - Domicilio: " + domicilio;
    }
}
