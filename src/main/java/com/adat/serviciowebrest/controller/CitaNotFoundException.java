package com.adat.serviciowebrest.controller;

public class CitaNotFoundException extends RuntimeException {
	 public CitaNotFoundException() {
	 super();
	 }
	 public CitaNotFoundException(String message) {
	 super(message);
	 }
	 public CitaNotFoundException(long id) {
	 super("Product not found: " + id);
	 }
	}