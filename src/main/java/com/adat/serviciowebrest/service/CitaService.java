package com.adat.serviciowebrest.service;

import java.util.Optional;
import java.util.Set;
import com.adat.serviciowebrest.domain.Cita;

public interface CitaService {
	 Set<Cita> findAll();
	 Set<Cita> findByEspecialidad(String especialidad);
	 Optional<Cita> findById(long id);
	 Cita addCita(Cita cita);
	 Cita modifyCita(long id, Cita newCita);
	 void deleteCita(long id);
	}