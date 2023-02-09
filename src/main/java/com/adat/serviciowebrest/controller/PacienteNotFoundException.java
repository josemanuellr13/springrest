package com.adat.serviciowebrest.controller;

public class PacienteNotFoundException extends RuntimeException {
	 public PacienteNotFoundException() {
	 super();
	 }
	 public PacienteNotFoundException(String message) {
	 super(message);
	 }
	 public PacienteNotFoundException(long id) {
	 super("Product not found: " + id);
	 }
	}