package com.backend.pruebaclinicaOdontologica;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.pruebaclinicaOdontologica.exception.BadRequestException;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.pruebaclinicaOdontologica.service.impl.TurnoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TurnoServiceTest
{
    @Autowired
    TurnoService turnoService;

    //REGISTRAR
    @Test
    public void debeRegistrarUnTurno() throws BadRequestException {
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(2L, 2L, LocalDateTime.parse("2023-12-31T14:30"));

        TurnoSalidaDto rtaObtenida = turnoService.registrarTurno(turnoEntradaDto);

        Assertions.assertEquals(2, rtaObtenida.getOdontologoTurnoSalidaDto().getId());
    }

    //LISTAR
    @Test
    public void debeListarTodosLosTurnos()
    {
        ArrayList listaTurnos = new ArrayList<>(turnoService.listarTurnos());
        assertNotNull(listaTurnos);
    }

    //MODIFICAR
    @Test
    public void debeModificarUnTurno() throws ResourceNotFoundException {
        TurnoModificacionEntradaDto turnoModificado = new TurnoModificacionEntradaDto(1L,3L,2L,LocalDateTime.parse("2023-10-18T14:30"));

        Assertions.assertEquals(3L, turnoService.modificarTurno(turnoModificado).getOdontologoTurnoSalidaDto().getId());
    }

    //BUSCAR
    @Test
    public void debeBuscarUnTurnoPorId()
    {
        Assertions.assertEquals(3L, turnoService.buscarTurnoPorId(3L).getId());
    }

    //ELIMINAR
    @Test
    public void debeEliminarUnTurnoPorId() throws ResourceNotFoundException {
        turnoService.eliminarTurno(1L);
        Assertions.assertNull(turnoService.buscarTurnoPorId(1L) );
    }


}
