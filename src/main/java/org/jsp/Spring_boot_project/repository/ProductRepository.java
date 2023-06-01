package org.jsp.Spring_boot_project.repository;

import java.util.List;

import org.jsp.Spring_boot_project.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByStatus(boolean b);

	Product findByPname(String name);

}
