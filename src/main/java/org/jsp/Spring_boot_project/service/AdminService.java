package org.jsp.Spring_boot_project.service;

import java.util.List;

import org.jsp.Spring_boot_project.dao.ProductDao;
import org.jsp.Spring_boot_project.dto.Product;
import org.jsp.Spring_boot_project.exception.AllException;
import org.jsp.Spring_boot_project.helper.Login;
import org.jsp.Spring_boot_project.helper.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	@Autowired
	ProductDao dao ;

	public ResponseStructure<Login> login(Login login) throws AllException {
		ResponseStructure<Login> structure= new ResponseStructure<>();
		if(login.getId().equals("admin")) {
			if(login.getPassword().equals("admin")) {
			structure.setData(login);
			structure.setMessage("login sucssefully");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return structure;
			
		}else {
			throw new AllException("invalid password");
		}}else {
		throw new AllException("invalid id");
	}
	}

	public ResponseStructure<List<Product>> fetchproduct() throws AllException {
		// TODO Auto-generated method stub
		ResponseStructure<List<Product>> structure= new ResponseStructure<>();
		
		List<Product>  list = dao.findall();
		
		
		if(list.isEmpty()) {
			throw new AllException("no product found");
		}else {
			structure.setMessage("product found");
			structure.setData(list);
			structure.setStatuscode(HttpStatus.FOUND.value());
	return structure;		
		}
	
	}

	public ResponseStructure<Product> changestatus(int pid) {
		// TODO Auto-generated method stub
		ResponseStructure<Product> structure= new ResponseStructure<>();
		
	Product product =	dao.find(pid);
	if(product.isStatus())

		product.setStatus(false);
	else
		product.setStatus(true);
		structure.setMessage("Changed status successfully");
	structure.setData(dao.save(product));
	structure.setStatuscode(HttpStatus.ACCEPTED.value());
		
		
		
		
		
		return structure;
	}
}
