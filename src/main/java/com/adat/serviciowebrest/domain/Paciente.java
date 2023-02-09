package com.adat.serviciowebrest.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pacientes")
public class Paciente implements Serializable{
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long id;
	 
	 @Column
	 private String nombre;
	 
	 @Column
	 private boolean genero;
	 
	 @Column
	 private int edad;
	 
	 @Column(nullable = true)
	 private Integer telefono;
	 
	 @Column
	 private String poblacion;
	 
	 @JsonIgnore
	 @OneToMany(mappedBy = "paciente")
	 private List<Cita> citas;
}