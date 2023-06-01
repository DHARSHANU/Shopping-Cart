package org.jsp.Spring_boot_project.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.Spring_boot_project.dto.Product;
import org.jsp.Spring_boot_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class ProductDao {
	
	@Autowired
	ProductRepository productRepository ;

	public Product save(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}
	
	public Product find(int pid) {
		Optional< Product>optional = productRepository.findById(pid);
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
	}

	public void deleteproduct(int pid) {
		// TODO Auto-generated method stub
	productRepository.deleteById(pid);	
	}

	public List<Product> findall() {
		return productRepository.findAll();
	}

	public List<Product> fetchcustomerproducts() {
		// TODO Auto-generated method stub
		return productRepository.findByStatus(true);	
				}
public Product find(String name) {
	return productRepository.findByPname(name);
}
}
