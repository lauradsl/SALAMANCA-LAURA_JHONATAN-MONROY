package com.backend.pruebaclinicaOdontologica.service.impl;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.pruebaclinicaOdontologica.entity.Odontologo;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.pruebaclinicaOdontologica.repository.OdontologoRepository;
import com.backend.pruebaclinicaOdontologica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<Odontologo> listaOdontologos = odontologoRepository.findAll();
        LOGGER.info("Lista de odontologos es: {}", listaOdontologos);
        return listaOdontologos.stream().map(this::entidadToDtoSalida).toList();
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {
        Odontologo odontologoGuardado = odontologoRepository.save(modelMapper.map(odontologo, Odontologo.class));
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoGuardado, OdontologoSalidaDto.class);
        LOGGER.info("Odontologo guardado: {}", odontologoSalidaDto);
        return odontologoSalidaDto;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);

        OdontologoSalidaDto odontologoSalidaDto = null;
        if (odontologoBuscado != null) {
            odontologoSalidaDto = entidadToDtoSalida(odontologoBuscado);
            LOGGER.info("Odontologo encontrado: {}", odontologoSalidaDto);
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return odontologoSalidaDto;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException
    {
        Odontologo odontologoAbuscar = odontologoRepository.findById(id).orElse(null);
        if (odontologoAbuscar != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se elimino el odontolo con ID {}", id);
        } else {
            LOGGER.error("Odontologo no encontrado");
            throw new ResourceNotFoundException("No se ha encontrado el odontologo con id: " + id);
        }
    }

    @Override
    public OdontologoSalidaDto modificarOdontologo(OdontologoModificacionEntradaDto odontologoModificado) {
        OdontologoSalidaDto odontologoSalidaDto = null;
        if (buscarOdontologoPorId(odontologoModificado.getId()) != null) {
            odontologoSalidaDto = entidadToDtoSalida(odontologoRepository.save(dtoModificadoToEntidad(odontologoModificado)));
            LOGGER.info("El odontologo {} fue modificado exitosamente ", odontologoModificado);
        } else {
            LOGGER.info("El odontologo {} no fue encontrado", odontologoModificado);
        }
        return odontologoSalidaDto;
    }


    //METODOS
    public Odontologo dtoEntradaToEntidad(OdontologoEntradaDto odontologoEntradaDto) {
        return modelMapper.map(odontologoEntradaDto, Odontologo.class);
    }

    public OdontologoSalidaDto entidadToDtoSalida(Odontologo odontologo) {
        return modelMapper.map(odontologo, OdontologoSalidaDto.class);
    }

    public Odontologo dtoModificadoToEntidad(OdontologoModificacionEntradaDto odontologoModificado) {
        return modelMapper.map(odontologoModificado, Odontologo.class);
    }

}
