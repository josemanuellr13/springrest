package com.adat.serviciowebrest.service;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adat.serviciowebrest.controller.ProductNotFoundException;
import com.adat.serviciowebrest.domain.Cita;
import com.adat.serviciowebrest.domain.Paciente;
import com.adat.serviciowebrest.domain.Product;
import com.adat.serviciowebrest.repository.PacienteRepository;

@Service
public class PacienteServiceImpl implements PacienteService {
	
	 @Autowired
	 private PacienteRepository pacienteRepository;
	 
	 @Override
	 public Set<Paciente> findAll() {
		 return pacienteRepository.findAll();
	 }

	@Override
	public Set<Paciente> findByPoblacion(String poblacion) {
		return pacienteRepository.findByPoblacion(poblacion);
	}

	@Override
	public Optional<Paciente> findById(long id) {
		return pacienteRepository.findById(id);
	}

	@Override
	public Paciente addPaciente(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}

	@Override
	public Paciente modifyPaciente(long id, Paciente newPaciente) {
		Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		newPaciente.setId(paciente.getId());
		return pacienteRepository.save(newPaciente);
	}

	@Override
	public void deletePaciente(long id) {
		pacienteRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		pacienteRepository.deleteById(id);
	}
	 
	
}
