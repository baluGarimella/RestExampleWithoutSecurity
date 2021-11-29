package com.crud.example.crud.example;

import java.util.List;
import java.util.Optional;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repo;

	public List<Product> listAll() {
		return repo.findAll();
	}

	public Product save(Product product) {
		return repo.save(product);
	}

	public Product get(long id) {

		return repo.findById(id).get();
	}

	public List<Product> getByName(String name) {

		return repo.findByName(name);

	}

	public void delete(long id) {
		repo.deleteById(id);
	}

	public Product update(Product product) throws RelationNotFoundException {
		Optional<Product> productDb = repo.findById(product.getId());
		if (productDb.isPresent()) {
			Product productUpdate = productDb.get();
			productUpdate.setId(product.getId());
			productUpdate.setName(product.getName());
			productUpdate.setPrice(product.getPrice());
			repo.save(productUpdate);
			return productUpdate;
		} else {
			throw new RelationNotFoundException("Record not found with id : " + product.getId());
		}
	}
}
