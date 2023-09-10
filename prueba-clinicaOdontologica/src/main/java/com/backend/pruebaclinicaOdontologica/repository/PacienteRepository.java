package com.backend.pruebaclinicaOdontologica.repository;

import com.backend.pruebaclinicaOdontologica.entity.Odontologo;
import com.backend.pruebaclinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
