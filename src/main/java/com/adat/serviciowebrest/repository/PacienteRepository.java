package com.adat.serviciowebrest.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.adat.serviciowebrest.domain.Paciente;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Long> {
	 Set<Paciente> findAll();
	 Set<Paciente> findByPoblacion(String poblacion);
}