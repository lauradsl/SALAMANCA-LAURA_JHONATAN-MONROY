package com.backend.pruebaclinicaOdontologica.service.impl;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.pruebaclinicaOdontologica.entity.Paciente;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.pruebaclinicaOdontologica.repository.PacienteRepository;
import com.backend.pruebaclinicaOdontologica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService
{
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper)
    {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes()
    {
        List<Paciente> listaPacientes = pacienteRepository.findAll();
        LOGGER.info("Lista de pacientes es: {}", listaPacientes);
        return listaPacientes.stream().map(this::entidadADtoSalida).toList();
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente)
    {
        Paciente pacienteGuardado = pacienteRepository.save(modelMapper.map(paciente, Paciente.class));
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteGuardado, PacienteSalidaDto.class);
        LOGGER.info("Paciente guardado: {}", pacienteSalidaDto);
        return pacienteSalidaDto;
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(Long id)
    {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);

        PacienteSalidaDto pacienteSalidaDto = null;
        if(pacienteBuscado != null){
            pacienteSalidaDto = entidadADtoSalida(pacienteBuscado);
            LOGGER.info("Paciente encontrado: {}", pacienteSalidaDto);
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return pacienteSalidaDto;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        Paciente pacienteAbuscar = pacienteRepository.findById(id).orElse(null);
        if (pacienteAbuscar != null)
        {
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se elimino el paciente con ID {}", id);
        } else {
            LOGGER.error("Paciente no encontrado");
            throw new ResourceNotFoundException("No se ha encontrado el paciente con id " + id);
        }
    }

    @Override
    public PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado)
    {
        PacienteSalidaDto pacienteSalidaDto = null;
        if(buscarPacientePorId(pacienteModificado.getId()) != null)
        {
            pacienteSalidaDto = entidadADtoSalida(pacienteRepository.save(dtoModificadoAEntidad(pacienteModificado)));
            LOGGER.info("El paciente {} fue modificado exitosamente ", pacienteModificado);
        } else {
            LOGGER.info("El paciente {} no fue encontrado", pacienteModificado);
        }
        return pacienteSalidaDto;
    }


    //se debe mapear tambien el domicilio. AGREGAR AL CONSTRUCTOR
    private void configureMapping()
    {
        //referencia al metodo. El domicilio de la entreda debe estar en el domicilio de Paciente. Se hace paralelimos entre domicilios
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
        .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilio,Paciente::setDomicilio));

        //mapeo para salida. De paciente a PacienteSalidaDto
        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio,PacienteSalidaDto::setDomicilio));

        //mapeo para modificaciones
        modelMapper.typeMap(PacienteModificacionEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteModificacionEntradaDto::getDomicilio, Paciente::setDomicilio));
    }

    //otro metodo que permita para retornar Paciente. Mapear el paciente que llega a Paciente Class
    public Paciente dtoEntradaAEntidad(PacienteEntradaDto pacienteEntradaDto)
    {
        return modelMapper.map(pacienteEntradaDto,Paciente.class);
    }

    public PacienteSalidaDto entidadADtoSalida(Paciente paciente)
    {
        return modelMapper.map(paciente, PacienteSalidaDto.class);
    }

    public Paciente dtoModificadoAEntidad(PacienteModificacionEntradaDto pacienteEntradaDto)
    {
        return modelMapper.map(pacienteEntradaDto,Paciente.class);
    }


}
