package com.backend.pruebaclinicaOdontologica;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.pruebaclinicaOdontologica.service.impl.OdontologoService;
import com.backend.pruebaclinicaOdontologica.service.impl.PacienteService;
import com.backend.pruebaclinicaOdontologica.service.impl.TurnoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TurnoServiceTest
{
    @Autowired
    TurnoService turnoService;

    @Autowired
    PacienteService pacienteService;
    private static PacienteEntradaDto paciente;
    @Autowired
    OdontologoService odontologoService;
    private static OdontologoEntradaDto odontologo;


    @BeforeAll
    static void doBefore()
    {
        paciente = new PacienteEntradaDto("Luisa", "Lotero", 123, LocalDate.parse("2023-12-31"), new DomicilioEntradaDto("El Tunal", 302, "Armenia", "Quindio"));

        odontologo = new OdontologoEntradaDto("LS-1234567", "Laura", "Salamanca");
    }

    //REGISTRAR
    @Test
    @Order(1)
    public void debeRegistrarUnTurno()  {

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(paciente);

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologo);

        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(pacienteSalidaDto.getId(), odontologoSalidaDto.getId(), LocalDateTime.of(2023, 12, 31,14,30));

        try
        {
            TurnoSalidaDto rtaObtenida = turnoService.registrarTurno(turnoEntradaDto);
            Assertions.assertEquals(1L, rtaObtenida.getOdontologoTurnoSalidaDto().getId());
        } catch (Exception e)
            {
                e.printStackTrace();
            }
    }

    //LISTAR
    @Test
    @Order(2)
    public void debeListarTodosLosTurnos()
    {
        List<TurnoSalidaDto> listaTurnos = turnoService.listarTurnos();
        assertTrue(listaTurnos.size() > 0);
    }

    //MODIFICAR
    @Test
    @Order(3)
    public void alIntentarModificarElTurnoId2_deberiaLanzarUnaResourceNotFoundException() {
        TurnoModificacionEntradaDto turnoModificacionEntradaDto = new TurnoModificacionEntradaDto();
        turnoModificacionEntradaDto.setId(2L);
        assertThrows(ResourceNotFoundException.class, () -> turnoService.modificarTurno(turnoModificacionEntradaDto));
    }

    //BUSCAR
    @Test
    @Order(4)
    public void debeBuscarUnTurnoPorId()
    {
        Assertions.assertEquals(1L, turnoService.buscarTurnoPorId(1L).getId());
    }

    //ELIMINAR
    @Test
    @Order(5)
    public void debeEliminarUnTurnoPorId() {

        try
        {
            turnoService.eliminarTurno(1L);
        } catch (Exception e)
            {
                e.printStackTrace();
            }
        assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
    }

}
