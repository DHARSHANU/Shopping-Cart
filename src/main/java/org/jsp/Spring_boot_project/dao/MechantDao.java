package org.jsp.Spring_boot_project.dao;

import java.util.Optional;

import org.jsp.Spring_boot_project.dto.Merchant;
import org.jsp.Spring_boot_project.helper.Login;
import org.jsp.Spring_boot_project.helper.ResponseStructure;
import org.jsp.Spring_boot_project.repository.Merchentrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MechantDao {
	
	@Autowired
	Merchentrepository merchentrepository;
	public Merchant save(Merchant merchant) {
		// TODO Auto-generated method stub
		return merchentrepository.save(merchant);
	}
	
	public Merchant find(String id) {
		// TODO Auto-generated method stub
	Optional<Merchant>optional	=merchentrepository.findById(id);
	if (optional.isEmpty()) {
		return null;
		
	} else {
      return optional.get();
	}
	}

}
