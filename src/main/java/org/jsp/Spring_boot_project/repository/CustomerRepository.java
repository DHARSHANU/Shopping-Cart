package org.jsp.Spring_boot_project.repository;

import java.util.Optional;

import org.jsp.Spring_boot_project.dto.Customer;
import org.jsp.Spring_boot_project.dto.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, String>{



}
