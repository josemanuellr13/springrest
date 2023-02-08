package com.adat.serviciowebrest.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long id;
	 
	 @Column
	 private String name;
	 
	 @Column
	 private String description;
	 
	 @Column
	 private String category;
	 
	 @Column
	 private float price;
	 
	 @Column(name = "creation_date")
	 private LocalDateTime creationDate;
}