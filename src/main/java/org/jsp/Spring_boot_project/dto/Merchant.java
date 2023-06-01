package org.jsp.Spring_boot_project.dto;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.sym.Name;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Entity
@Data
@Component

public class Merchant {

	@GeneratedValue(generator = "merchant")
	@GenericGenerator(name = "merchant",strategy = "org.jsp.Spring_boot_project.helper.Merchantid")
	@Id
	String id;
	String name;
	String email;
	String password;
	long mobile;
	boolean status;
	double wallet;
	int otp;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<Product> products;
}
