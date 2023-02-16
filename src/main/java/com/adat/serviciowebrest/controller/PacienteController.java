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
	 
	 // Obtenemos pacientes V
	 @GetMapping("/pacientes")
	 public ResponseEntity<Set<Paciente>> getPacientes() {
		 logger.info("Obtenemos todos los pacientes");
		 Set<Paciente> pacientes = null;
		 pacientes = pacienteService.findAll();
		 
		 return new ResponseEntity<>(pacientes, HttpStatus.OK);
	 }
	 
	 
	 // Obtenemos paciente V
	 @GetMapping("/pacientes/{id}")
	 public ResponseEntity<Paciente> getPacientes(@PathVariable long id) throws PacienteNotFoundException {
		 logger.info("Obtenemos paciente " + id);
		 Paciente paciente = pacienteService.findById(id).orElseThrow(() -> new PacienteNotFoundException(id));
		 return new ResponseEntity<>(paciente, HttpStatus.OK);
	 }
	 
	 
	 // Publicamos paciente V
	 @PostMapping("/pacientes")
	 public ResponseEntity<Paciente> addPaciente(@RequestBody Paciente paciente) {
		 logger.info("Publicando paciente nuevo con ID " + paciente.getId());
		 Paciente addedPaciente = pacienteService.addPaciente(paciente);
		 
		 if(paciente.getCitas() != null) {
			 for(int i = 0; i < paciente.getCitas().size() ; i++) {
				 paciente.getCitas().get(i).setPaciente(addedPaciente);
			 } 
		 }
		 
		 return new ResponseEntity<>(addedPaciente, HttpStatus.CREATED);
	 }
	 
	 /*{
		    "id": 33,
		    "nombre": "Isae",
		    "genero": true,
		    "edad": 46,
		    "telefono": null,
		    "poblacion": "Dos Hermanas",
		    "citas": [
		    ]
		}
			  
	  */
	 
	 
	 
	 // Actualizamos paciente V
	 @PutMapping("/pacientes/{id}")
	 public ResponseEntity<Paciente> modifyPaciente(@PathVariable long id, @RequestBody Paciente newPaciente) {
		 logger.info("Actualizando paciente " + id);
		 Paciente paciente = pacienteService.modifyPaciente(id, newPaciente);
		 return new ResponseEntity<>(paciente, HttpStatus.OK);
	 } /*
	 
			 {
		    "id": 3,
		    "nombre": "Patricia",
		    "genero": false,
		    "edad": 16,
		    "telefono": null,
		    "poblacion": "Dos Hermanas",
		    "citas": []
		}
	 
	 */
	 
	 
	 // Borramos paciente V
	 @DeleteMapping("/pacientes/{id}")
	 public ResponseEntity<Response> deletePaciente(@PathVariable long id) {
		 logger.info("Borrando paciente " + id);
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
