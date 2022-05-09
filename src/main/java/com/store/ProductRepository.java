package com.store;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("select p from products p where p.id=?1")
	public List<Product> findProductById(int id);
	
	@Query("select p from products p where p.name=?1")
	public List<Product> findProductByName(String name);
}
