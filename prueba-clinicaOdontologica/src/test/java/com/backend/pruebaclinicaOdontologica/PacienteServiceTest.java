package com.backend.pruebaclinicaOdontologica;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.DomicilioModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.pruebaclinicaOdontologica.service.impl.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PacienteServiceTest
{
    @Autowired
    PacienteService pacienteService;

    //REGISTRAR
    @Test
    public void debeRegistrarUnPaciente()
    {
        DomicilioEntradaDto domicilioEntradaDto = new DomicilioEntradaDto("El Tunal", 302, "Armenia", "Quindio");

        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Eunice", "Lotero", 123, LocalDate.parse("2023-12-31"), domicilioEntradaDto);

        PacienteSalidaDto rtaObtenida = pacienteService.registrarPaciente(pacienteEntradaDto);

        Assertions.assertEquals("Eunice", rtaObtenida.getNombre());
    }

    //LISTAR
    @Test
    public void debeListarTodosLosPacientes()
    {
        ArrayList listaPacientes = new ArrayList<>(pacienteService.listarPacientes());
        assertNotNull(listaPacientes);
    }

    //MODIFICAR
    @Test
    public void debeModificarUnPaciente()
    {
        DomicilioModificacionEntradaDto domicilioModificacionEntradaDto = new DomicilioModificacionEntradaDto(3L, "El Tunal", 302, "Armenia", "Quindio");

        PacienteModificacionEntradaDto pacienteModificado = new PacienteModificacionEntradaDto(3L,"Mariela", "Lotero", 123, LocalDate.parse("2023-12-31"), domicilioModificacionEntradaDto);

        PacienteSalidaDto rtaPacienteModificado = pacienteService.modificarPaciente(pacienteModificado);

        assertEquals("Mariela", rtaPacienteModificado.getNombre());
    }

    //BUSCAR
    @Test
    public void debeBuscarUnPacientePorId()
    {
        assertEquals("Mariela", pacienteService.buscarPacientePorId(3L).getNombre());
    }

    //ELIMINAR
    @Test
    public void debeEliminarUnPacientePorId() throws ResourceNotFoundException
    {
        pacienteService.eliminarPaciente(3L);
        Assertions.assertNull(pacienteService.buscarPacientePorId(3L));
    }
}
