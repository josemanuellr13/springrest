package com.adat.serviciowebrest.controller;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.adat.serviciowebrest.domain.Cita;
import com.adat.serviciowebrest.domain.Paciente;
import com.adat.serviciowebrest.service.CitaService;
import com.adat.serviciowebrest.service.PacienteService;

import static com.adat.serviciowebrest.controller.Response.NOT_FOUND;

@RestController
public class PacienteController {
	private final Logger logger = LoggerFactory.getLogger(PacienteController.class);
	
	 @Autowired
	 private PacienteService pacienteService;
	 
	 @GetMapping("/pacientes")
	 public ResponseEntity<Set<Paciente>> getPacientes() {
		 logger.info("inicio getProducts");
		 Set<Paciente> pacientes = null;
		 pacientes = pacienteService.findAll();
		 
		 return new ResponseEntity<>(pacientes, HttpStatus.OK);
	 }
	 
	 
	 @GetMapping("/pacientes/{id}")
	 public ResponseEntity<Paciente> getPacientes(@PathVariable long id) throws PacienteNotFoundException {
		 Paciente paciente = pacienteService.findById(id).orElseThrow(() -> new PacienteNotFoundException(id));
		 return new ResponseEntity<>(paciente, HttpStatus.OK);
	 }
	 
	 
	 @PostMapping("/pacientes")
	 public ResponseEntity<Paciente> addPaciente(@RequestBody Paciente paciente) {
		 Paciente addedPaciente = pacienteService.addPaciente(paciente);
		 return new ResponseEntity<>(addedPaciente, HttpStatus.CREATED);
	 }
	 
	 
	 @PutMapping("/pacientes/{id}")
	 public ResponseEntity<Paciente> modifyPaciente(@PathVariable long id, @RequestBody Paciente newPaciente) {
		 Paciente paciente = pacienteService.modifyPaciente(id, newPaciente);
		 return new ResponseEntity<>(paciente, HttpStatus.OK);
	 }
	 
	 
	 @DeleteMapping("/pacientes/{id}")
	 public ResponseEntity<Response> deletePaciente(@PathVariable long id) {
		 pacienteService.deletePaciente(id);
		 return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
	 }
	 
	 
	 @ExceptionHandler(PacienteNotFoundException.class)
	 @ResponseBody
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 public ResponseEntity<Response> handleException(PacienteNotFoundException pnfe) {
		 logger.error(pnfe.getMessage() , pnfe);
		 Response response = Response.errorResonse(NOT_FOUND, pnfe.getMessage());
		 return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	 }
}
