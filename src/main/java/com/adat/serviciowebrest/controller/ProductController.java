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
import com.adat.serviciowebrest.domain.Product;
import com.adat.serviciowebrest.service.ProductService;
import static com.adat.serviciowebrest.controller.Response.NOT_FOUND;

@RestController
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	 @Autowired
	 private ProductService productService;
	 
	 @GetMapping("/products")
	 public ResponseEntity<Set<Product>> getProducts(@RequestParam(value = "category", defaultValue = "") String category) {
		 logger.info("inicio getProducts");
		 Set<Product> products = null;
		 if (category.equals(""))
			 products = productService.findAll();
		 else
			 products = productService.findByCategory(category);
		 return new ResponseEntity<>(products, HttpStatus.OK);
	 }
	 
	 
	 @GetMapping("/products/{id}")
	 public ResponseEntity<Product> getProduct(@PathVariable long id) throws ProductNotFoundException {
		 Product product = productService.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		 return new ResponseEntity<>(product, HttpStatus.OK);
	 }
	 
	 
	 @PostMapping("/products")
	 public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		 Product addedProduct = productService.addProduct(product);
		 return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
	 }
	 
	 
	 @PutMapping("/products/{id}")
	 public ResponseEntity<Product> modifyProduct(@PathVariable long id, @RequestBody Product newProduct) {
		 Product product = productService.modifyProduct(id, newProduct);
		 return new ResponseEntity<>(product, HttpStatus.OK);
	 }
	 
	 
	 @DeleteMapping("/products/{id}")
	 public ResponseEntity<Response> deleteProduct(@PathVariable long id) {
		 productService.deleteProduct(id);
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
