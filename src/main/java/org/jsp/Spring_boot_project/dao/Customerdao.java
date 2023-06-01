package org.jsp.Spring_boot_project.dao;

import java.util.Optional;

import org.jsp.Spring_boot_project.dto.Customer;
import org.jsp.Spring_boot_project.dto.Merchant;
import org.jsp.Spring_boot_project.repository.CustomerRepository;
import org.jsp.Spring_boot_project.repository.Merchentrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Customerdao {

	
	@Autowired
	CustomerRepository customerRepository ;
	
	
	public Customer find(String id) {
		// TODO Auto-generated method stub
	Optional<Customer>optional	=customerRepository.findById(id);
	if (optional.isEmpty()) {
		return null;
		
	} else {
      return optional.get();
	}
	}

	public Customer save(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}


	


}
