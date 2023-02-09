package com.adat.serviciowebrest.service;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adat.serviciowebrest.controller.ProductNotFoundException;
import com.adat.serviciowebrest.domain.Cita;
import com.adat.serviciowebrest.domain.Product;
import com.adat.serviciowebrest.repository.CitaRepository;
import com.adat.serviciowebrest.repository.ProductRepository;

@Service
public class CitaServiceImpl implements CitaService {
	
	 @Autowired
	 private CitaRepository citaRepository;
	 
	 @Override
	 public Set<Cita> findAll() {
		 return citaRepository.findAll();
	 }
	 
	@Override
	public Set<Cita> findByEspecialidad(String especialidad) {
		return citaRepository.findByEspecialidad(especialidad);
	}
	
	@Override
	public Optional<Cita> findById(long id) {
		return citaRepository.findById(id);
	}
	
	
	@Override
	public Cita addCita(Cita cita) {
		return citaRepository.save(cita);
	}
	
	
	@Override
	public Cita modifyCita(long id, Cita newCita) {
		Cita cita = citaRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		newCita.setId(cita.getId());
		return citaRepository.save(newCita);
	}
	
	
	@Override
	public void deleteCita(long id) {
		citaRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		citaRepository.deleteById(id);
	}
}
