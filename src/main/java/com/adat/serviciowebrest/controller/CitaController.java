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
public class CitaController {
	private final Logger logger = LoggerFactory.getLogger(CitaController.class);
	
	 @Autowired
	 private CitaService citaService;
	 @Autowired
	 private PacienteService pacienteService;
	 
	 // Obtenemos todas las citas V
	 @GetMapping("/citas")
	 public ResponseEntity<Set<Cita>> getCitas(@RequestParam(value = "especialidad", defaultValue = "") String especialidad) {
		 logger.info("Obteniendo citas");
		 Set<Cita> citas = null;
		 if (especialidad.equals(""))
			 citas = citaService.findAll();
		 else
			 citas = citaService.findByEspecialidad(especialidad);
		 
		 return new ResponseEntity<>(citas, HttpStatus.OK);
	 }
	 
	 // Dado el ID de un paciente, obtenemos sus citas V
	 @GetMapping("/citasdelpaciente/{id}")
	 public ResponseEntity<Set<Cita>> getCitasByPaciente(@PathVariable long id) {
		 
		 logger.info("Obteniendo citas del paciente " + id);
		 Paciente pac = pacienteService.findById(id).get();
		 Set<Cita> citas = null;
		 citas = citaService.findCitasByPaciente(pac);
		 
		 return new ResponseEntity<>(citas, HttpStatus.OK);
	 }
	 
	 
	 // Obtenemos una cita V
	 @GetMapping("/citas/{id}")
	 public ResponseEntity<Cita> getCitas(@PathVariable long id) throws CitaNotFoundException {
		 logger.info("Obteniendo la cita " + id);
		 Cita cita = citaService.findById(id).orElseThrow(() -> new CitaNotFoundException(id));
		 return new ResponseEntity<>(cita, HttpStatus.OK);
	 }
	 
	 
	 // Posteamos una cita V
	 @PostMapping("/citas")
	 public ResponseEntity<Cita> addCita(@RequestBody Cita cita) {
		 Cita addedCita = citaService.addCita(cita);
		 return new ResponseEntity<>(addedCita, HttpStatus.CREATED);
	 }
	 
	 // Modificamos cita V
	 @PutMapping("/citas/{id}")
	 public ResponseEntity<Cita> modifyProduct(@PathVariable long id, @RequestBody Cita newCita) {
		 Cita cita = citaService.modifyCita(id, newCita);
		 return new ResponseEntity<>(cita, HttpStatus.OK);
	 }
	 
	/* {
	        "id": 3,
	        "fecha": "2023-02-01T00:00:00",
	        "numeroConsulta": 2,
	        "especialidad": "Pediattra",
	        "paciente": {
	            "id": 2,
	            "nombre": "Marie Candela Gutierrez",
	            "genero": true,
	            "edad": 37,
	            "telefono": 643233123,
	            "poblacion": "Utrera"
	        }
	    }
	 */
	 
	 
	 // Borramos cita V
	 @DeleteMapping("/citas/{id}")
	 public ResponseEntity<Response> deleteProduct(@PathVariable long id) {
		 logger.info("Borrando la cita " + id);
		 citaService.deleteCita(id);
		 return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
	 }
	 
	 
	 @ExceptionHandler(CitaNotFoundException.class)
	 @ResponseBody
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 public ResponseEntity<Response> handleException(CitaNotFoundException pnfe) {
		 logger.error(pnfe.getMessage() , pnfe);
		 Response response = Response.errorResonse(NOT_FOUND, pnfe.getMessage());
		 return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	 }
}
