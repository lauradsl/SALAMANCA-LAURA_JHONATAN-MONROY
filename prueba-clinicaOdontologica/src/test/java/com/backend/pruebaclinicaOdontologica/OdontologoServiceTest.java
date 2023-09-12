package com.backend.pruebaclinicaOdontologica;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.pruebaclinicaOdontologica.service.impl.OdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;
    //REGISTRAR
    @Test
    @Order(1)
    public void debeRegistrarUnOdontologo()
    {
        OdontologoEntradaDto odontologoEntradaDto =  new OdontologoEntradaDto("LS-1234567", "Laura", "Salamanca");

        OdontologoSalidaDto rtaObtenida = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertEquals("Laura", rtaObtenida.getNombre());
    }

    //LISTAR
    @Test
    @Order(2)
    public void debeListarTodosLosOdontologos()
    {
        List<OdontologoSalidaDto> listaOdontologos = odontologoService.listarOdontologos();
        assertTrue(listaOdontologos.size() > 0);
    }

    //MODIFICAR
    @Test
    @Order(3)
    public void debeModificarElNombreDeUnOdontologo()
    {
        OdontologoModificacionEntradaDto odontologoModificado = new OdontologoModificacionEntradaDto(1L,"LS-1234567", "Daniela", "Salamanca");

        OdontologoSalidaDto rtaOdontologoModificado = odontologoService.modificarOdontologo(odontologoModificado);

        assertEquals("Daniela", rtaOdontologoModificado.getNombre());
    }

    //BUSCAR
    @Test
    @Order(3)
    public void debeBuscarUnOdontologoPorId()
    {
        assertEquals("Daniela", odontologoService.buscarOdontologoPorId(1L).getNombre());
    }

    //ELIMINAR
    @Test
    public void debeEliminarUnOdontologoPorId()
    {
        try
        {
            odontologoService.eliminarOdontologo(1L);
        } catch (Exception e)
            {
                e.printStackTrace();
            }
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }
}
