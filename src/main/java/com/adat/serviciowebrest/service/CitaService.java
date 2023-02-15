package com.adat.serviciowebrest.service;

import java.util.Optional;
import java.util.Set;
import com.adat.serviciowebrest.domain.Cita;
import com.adat.serviciowebrest.domain.Paciente;

public interface CitaService {
	 Set<Cita> findAll();
	 Set<Cita> findByEspecialidad(String especialidad);
	 Set<Cita> findCitasByPaciente(Paciente pac);
	 Optional<Cita> findById(long id);
	 Cita addCita(Cita cita);
	 Cita modifyCita(long id, Cita newCita);
	 void deleteCita(long id);
	}