package com.adat.serviciowebrest.service;

import java.util.Optional;
import java.util.Set;
import com.adat.serviciowebrest.domain.Cita;
import com.adat.serviciowebrest.domain.Paciente;

public interface PacienteService {
	 Set<Paciente> findAll();
	 Set<Paciente> findByPoblacion(String poblacion);
	 Optional<Paciente> findById(long id);
	 Paciente addPaciente(Paciente paciente);
	 Paciente modifyPaciente(long id, Paciente newPaciente);
	 void deletePaciente(long id);
	}