package com.backend.pruebaclinicaOdontologica;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.pruebaclinicaOdontologica.service.impl.OdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;
    //REGISTRAR
    @Test
    public void registrarOdontologo()
    {
        OdontologoEntradaDto odontologoEntradaDto =  new OdontologoEntradaDto("LS-1234567", "Laura", "Salamanca");

        OdontologoSalidaDto rtaObtenida = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertEquals("LS-1234567", rtaObtenida.getMatricula());
    }

    //LISTAR
    @Test
    public void debeListarTodosLosOdontologos()
    {
        ArrayList listaOdontologos = new ArrayList<>(odontologoService.listarOdontologos());
        assertNotNull(listaOdontologos);
    }

    //MODIFICAR
    @Test
    public void debeModificarUnOdontologo()
    {
        OdontologoModificacionEntradaDto odontologoModificado = new OdontologoModificacionEntradaDto(1L,"LS-1234567", "Daniela", "Salamanca");

        OdontologoSalidaDto rtaOdontologoModificado = odontologoService.modificarOdontologo(odontologoModificado);

        assertEquals("Daniela", rtaOdontologoModificado.getNombre());
    }

    //BUSCAR
    @Test
    public void debeBuscarUnOdontologoPorId()
    {
        assertEquals("Daniela", odontologoService.buscarOdontologoPorId(1L).getNombre());
    }

    //ELIMINAR
    @Test
    public void debeEliminarUnOdontologoPorId() throws ResourceNotFoundException
    {
        odontologoService.eliminarOdontologo(1L);
        assertNull(odontologoService.buscarOdontologoPorId(1L) );
    }
}
