package com.backend.pruebaclinicaOdontologica.service.impl;

import com.backend.pruebaclinicaOdontologica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.turno.OdontologoTurnoSalidaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.turno.PacienteTurnoSalidaDto;
import com.backend.pruebaclinicaOdontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.pruebaclinicaOdontologica.entity.Odontologo;
import com.backend.pruebaclinicaOdontologica.entity.Paciente;
import com.backend.pruebaclinicaOdontologica.entity.Turno;
import com.backend.pruebaclinicaOdontologica.exception.BadRequestException;
import com.backend.pruebaclinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.pruebaclinicaOdontologica.repository.TurnoRepository;
import com.backend.pruebaclinicaOdontologica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService
{
    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;
    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, OdontologoService odontologoService, PacienteService pacienteService)
    {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;

    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException {
        TurnoSalidaDto turnoSalidaDto;
        //ya no se trabaja con el repositorio sino con el servicio
        PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());
        OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(turnoEntradaDto.getOdontologoId());

        String pacienteNoEnBd = "El paciente no se encuentra en nuestra base de datos";
        String odontologoNoEnBd = "El odontologo no se encuentra en nuestra base de datos";

        if(paciente == null || odontologo == null)
        {
            if(paciente == null && odontologo == null)
            {
                LOGGER.error("El paciente y el odontologo no se encuentran en nuestra base de datos");
                throw new BadRequestException("No se encuentra el paciente con id: " + turnoEntradaDto.getPacienteId() + "ni el odontologo con id: " + turnoEntradaDto.getOdontologoId());
            } else if (paciente==null)
                {
                    LOGGER.error(pacienteNoEnBd);
                    throw new BadRequestException("No se encuentra el paciente con id: " + turnoEntradaDto.getPacienteId());
                } else
                    {
                        LOGGER.error(odontologoNoEnBd);
                        throw new BadRequestException("No se encuentra el odontologo con id: " + turnoEntradaDto.getOdontologoId());
                    }
        } else
            {
                Turno turnoNuevo = turnoRepository.save(modelMapper.map(turnoEntradaDto, Turno.class));
                turnoSalidaDto = entidadADto(turnoNuevo);
                LOGGER.info("Nuevo turno registrado con exito: {}", turnoSalidaDto);
            }
        return turnoSalidaDto;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos()
    {
        List<Turno> listaTurnos = turnoRepository.findAll();
        LOGGER.info("La lista de turno es: {}", listaTurnos);
        return listaTurnos.stream().map(this::entidadADto).toList();
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id)
    {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if(turnoBuscado != null){
            turnoSalidaDto = entidadADto(turnoBuscado);
            LOGGER.info("Turno encontrado: {}", turnoSalidaDto);
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return turnoSalidaDto;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException
    {
        Turno turnoAbuscar = turnoRepository.findById(id).orElse(null);
        if (turnoAbuscar != null)
        {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se elimino el turno con ID {}", id);
        } else {
            LOGGER.error("No se ha encontrado el turno con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el turno con id " + id);
        }
    }

    @Override
    public TurnoSalidaDto modificarTurno(TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException
    {
        Turno turnoModificar = turnoRepository.findById(turnoModificacionEntradaDto.getId()).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if (turnoModificar != null) {

            turnoModificar.setPaciente(modelMapper.map(pacienteService.buscarPacientePorId(turnoModificacionEntradaDto.getPacienteId()), Paciente.class));
            turnoModificar.setOdontologo(modelMapper.map(odontologoService.buscarOdontologoPorId(turnoModificacionEntradaDto.getOdontologoId()), Odontologo.class));
            turnoModificar.setFechaYHora(turnoModificacionEntradaDto.getFechaYHora());
            turnoRepository.save(turnoModificar);

            turnoSalidaDto = entidadADto(turnoModificar);

            LOGGER.warn("Turno actualizado: {}", turnoSalidaDto);

        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el turno no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos ya que el turno no se encuentra registrado");
        }

        return turnoSalidaDto;
    }


    public TurnoSalidaDto entidadADtoSalida(Turno turno)
    {
        return modelMapper.map(turno, TurnoSalidaDto.class);
    }

    private PacienteTurnoSalidaDto pacienteSalidaDtoASalidaTurnoDto(Long id) {
        return modelMapper.map(pacienteService.buscarPacientePorId(id), PacienteTurnoSalidaDto.class);
    }

    private OdontologoTurnoSalidaDto odontologoSalidaDtoASalidaTurnoDto(Long id) {
        return modelMapper.map(odontologoService.buscarOdontologoPorId(id), OdontologoTurnoSalidaDto.class);
    }

    private TurnoSalidaDto entidadADto(Turno turno) {
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turno, TurnoSalidaDto.class);
        turnoSalidaDto.setPacienteTurnoSalidaDto(pacienteSalidaDtoASalidaTurnoDto(turno.getPaciente().getId()));
        turnoSalidaDto.setOdontologoTurnoSalidaDto(odontologoSalidaDtoASalidaTurnoDto(turno.getOdontologo().getId()));
        return turnoSalidaDto;
    }


    /*private void configureMapping()
    {
        modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
                .addMappings(mapper -> mapper.map(Turno::getPaciente, TurnoSalidaDto::setPacienteTurnoSalidaDto))
                .addMappings(mapper -> mapper.map(Turno::getOdontologo, TurnoSalidaDto::setOdontologoTurnoSalidaDto));
    }
    */
}
