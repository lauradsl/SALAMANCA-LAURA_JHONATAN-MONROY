package com.backend.pruebaclinicaOdontologica;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.pruebaclinicaOdontologica.service.impl.PacienteService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class PacienteServiceTest
{
    @Autowired
    PacienteService pacienteService;

    //REGISTRAR
    @Test
    @Order(1)
    public void debeRegistrarUnPacienteConNombreLuisa()
    {
        DomicilioEntradaDto domicilioEntradaDto = new DomicilioEntradaDto("El Tunal", 302, "Armenia", "Quindio");

        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Luisa", "Lotero", 123, LocalDate.parse("2023-12-31"), domicilioEntradaDto);

        PacienteSalidaDto rtaObtenida = pacienteService.registrarPaciente(pacienteEntradaDto);

        Assertions.assertEquals("Luisa", rtaObtenida.getNombre());
    }

    //LISTAR
    @Test
    @Order(2)
    public void debeListarUnaListaNoVaciaDePacientes()
    {
        Assertions.assertTrue(pacienteService.listarPacientes().size() > 0);
    }

    //MODIFICAR
    @Test
    public void alIntentarModificarElPacienteId2_deberiaLanzarUnaResourceNotFoundException() {
        PacienteModificacionEntradaDto pacienteModificacionEntradaDto = new PacienteModificacionEntradaDto();
        pacienteModificacionEntradaDto.setId(2L);
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.modificarPaciente(pacienteModificacionEntradaDto));
    }

    //BUSCAR
    @Test
    @Order(3)
    public void debeBuscarUnPacientePorNombreLuisa()
    {
        assertEquals("Luisa", pacienteService.buscarPacientePorId(3L).getNombre());
    }

    //ELIMINAR
    @Test
    @Order(4)
    public void alIntetarEliminarUnPacienteYaEliminado_deberiaLanzarUnResourceNotFoundException()
    {
        try
        {
            pacienteService.eliminarPaciente(1L);
        }catch (Exception e)
            {
                e.printStackTrace();
            }
        assertThrows(ResourceNotFoundException.class,() -> pacienteService.eliminarPaciente(1L));
    }
}
