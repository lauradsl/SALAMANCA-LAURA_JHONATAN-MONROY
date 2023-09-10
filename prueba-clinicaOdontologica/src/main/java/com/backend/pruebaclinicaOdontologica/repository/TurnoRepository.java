package com.backend.pruebaclinicaOdontologica.repository;

import com.backend.pruebaclinicaOdontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

    //@Query("Select t from TURNO t join t.paciente p where p.apellido = ?1")
    //List<Turno> listarTurnosPorApellidoPaciente(String apellido);

    //@Query(value = "SELECT * FROM TURNO JOIN ODONTOLOGOS ON TURNOS.ODONTOLOGO_ID = ODONTOLOGOS.ID WHERE ODONTOLOGOS.APELLIDO = ?1", nativeQuery = true)
    //List<Turno> listarTurnosPorApellidoOdontologo(String apellido);

}
