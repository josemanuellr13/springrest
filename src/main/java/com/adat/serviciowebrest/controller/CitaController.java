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
import com.adat.serviciowebrest.service.CitaService;
import static com.adat.serviciowebrest.controller.Response.NOT_FOUND;

@RestController
public class CitaController {
	private final Logger logger = LoggerFactory.getLogger(CitaController.class);
	
	 @Autowired
	 private CitaService citaService;
	 
	 @GetMapping("/citas")
	 public ResponseEntity<Set<Cita>> getCitas(@RequestParam(value = "especialidad", defaultValue = "") String especialidad) {
		 logger.info("inicio getProducts");
		 Set<Cita> citas = null;
		 if (especialidad.equals(""))
			 citas = citaService.findAll();
		 else
			 citas = citaService.findByEspecialidad(especialidad);
		 
		 return new ResponseEntity<>(citas, HttpStatus.OK);
	 }
	 
	 
	 @GetMapping("/citas/{id}")
	 public ResponseEntity<Cita> getCitas(@PathVariable long id) throws ProductNotFoundException {
		 Cita cita = citaService.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		 return new ResponseEntity<>(cita, HttpStatus.OK);
	 }
	 
	 
	 @PostMapping("/citas")
	 public ResponseEntity<Cita> addCita(@RequestBody Cita cita) {
		 Cita addedCita = citaService.addCita(cita);
		 return new ResponseEntity<>(addedCita, HttpStatus.CREATED);
	 }
	 
	 
	 @PutMapping("/citas/{id}")
	 public ResponseEntity<Cita> modifyProduct(@PathVariable long id, @RequestBody Cita newCita) {
		 Cita cita = citaService.modifyCita(id, newCita);
		 return new ResponseEntity<>(cita, HttpStatus.OK);
	 }
	 
	 
	 @DeleteMapping("/citas/{id}")
	 public ResponseEntity<Response> deleteProduct(@PathVariable long id) {
		 citaService.deleteCita(id);
		 return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
	 }
	 
	 
	 @ExceptionHandler(ProductNotFoundException.class)
	 @ResponseBody
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 public ResponseEntity<Response> handleException(ProductNotFoundException pnfe) {
		 logger.error(pnfe.getMessage() , pnfe);
		 Response response = Response.errorResonse(NOT_FOUND, pnfe.getMessage());
		 return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	 }
}
