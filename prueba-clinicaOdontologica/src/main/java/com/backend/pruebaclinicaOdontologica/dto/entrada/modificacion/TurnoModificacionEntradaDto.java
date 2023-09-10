package com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion;


import com.backend.pruebaclinicaOdontologica.entity.Odontologo;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoModificacionEntradaDto
{
    //DESDE TURNO PUEDO CAMBIAR PACIENTE Y ODONTOLOGO????????? --> agregar paciente, recibe todos los campos de turnos para persistirlos en la bd

    @NotNull (message = "Debe ingresar un id")
    private Long id;

    @NotNull (message = "Debe ingresar el id del odontologo")
    private Long odontologoId;

    @NotNull (message = "Debe ingresar el id del paciente")
    private Long pacienteId;

    @NotNull(message = "Debe ingresar una fecha de ingreso")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime fechaYHora;

    public TurnoModificacionEntradaDto() {};

    public TurnoModificacionEntradaDto(Long id, Long odontologoId, Long pacienteId, LocalDateTime fechaYHora)
    {
        this.id = id;
        this.odontologoId = odontologoId;
        this.pacienteId = pacienteId;
        this.fechaYHora = fechaYHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
