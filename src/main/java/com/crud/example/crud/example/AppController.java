package com.crud.example.crud.example;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	@Autowired
	private ProductService service;

	@PostMapping("/newproduct/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {

		return ResponseEntity.ok().body(this.service.save(product));
	}

	@GetMapping("/product/allproducts")
	public ResponseEntity<List<Product>> getAllProducts() {

		return ResponseEntity.ok().body(this.service.listAll());
	}

	@GetMapping("/product/getproduct/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id) {

		return ResponseEntity.ok().body(this.service.get(id));
	}

	@DeleteMapping("/product/deleteproduct/{id}")
	public HttpStatus deleteProductById(@PathVariable long id) {
		this.service.delete(id);
		return HttpStatus.OK;
	}

	@PutMapping("/product/updateproduct")
	public ResponseEntity<Product> updateProductById(@RequestBody Product product) {
		try {
			return ResponseEntity.ok().body(this.service.update(product));
		} catch (RelationNotFoundException e) {
			throw new RuntimeException(e);

		}

	}

	@GetMapping("/product/getproductByName/{name}")
	public ResponseEntity<List<Product>> getProductByName(@PathVariable String name) throws RelationNotFoundException {
		List<Product> list = this.service.getByName(name);
		if (list.size() != 0) {

			return ResponseEntity.ok().body(this.service.getByName(name));
		} else {
			throw new RelationNotFoundException("Record not found with id : " + name);
		}

	}

	

}
