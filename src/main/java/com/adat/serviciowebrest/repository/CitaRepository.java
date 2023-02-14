package com.adat.serviciowebrest.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.adat.serviciowebrest.domain.Cita;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Long> {
	 Set<Cita> findAll();
	 Set<Cita> findByEspecialidad(String especialidad);
	 Set<Cita> findByPaciente(long id);
}