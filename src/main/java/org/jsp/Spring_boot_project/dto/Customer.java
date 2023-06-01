package org.jsp.Spring_boot_project.dto;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
@Component
public class Customer {
	
	@GeneratedValue(generator = "customer")
	@GenericGenerator(name = "customer",strategy = "org.jsp.Spring_boot_project.helper.Customerid")

	@Id
	String cust_id;
	String name;
	long mobile;
	String email;
	String address;
	boolean status;
	double wallet;
	int otp;
	String password;

	@OneToOne(cascade = CascadeType.ALL)
	Cart cart ;
   
	@OneToMany(cascade = CascadeType.ALL)
	List<ShoppingOrder> orders;
}

