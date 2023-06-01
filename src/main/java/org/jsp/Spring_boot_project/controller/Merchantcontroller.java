package org.jsp.Spring_boot_project.controller;

import java.util.List;

import org.jsp.Spring_boot_project.dto.Merchant;
import org.jsp.Spring_boot_project.dto.Product;
import org.jsp.Spring_boot_project.exception.AllException;
import org.jsp.Spring_boot_project.helper.Login;
import org.jsp.Spring_boot_project.helper.ResponseStructure;
import org.jsp.Spring_boot_project.service.Merchantservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("merchant")
public class Merchantcontroller {

	@Autowired
	Merchantservice merchantservice ;
	
	
	@PostMapping("signup")
	public ResponseStructure<Merchant> signup(@RequestBody Merchant merchant)
	{
		return merchantservice.signup(merchant);
		
	}
	
	@PutMapping("verify/{id}/{otp}")
	public ResponseStructure<Merchant> verfiy(@PathVariable String id, @PathVariable int otp) throws AllException
	{
		return merchantservice.verfiy(id,otp);
	}
	
	
	
	@PostMapping("login")
	public ResponseStructure<Merchant> login(@RequestBody Login  login) throws AllException
	
	{
		return merchantservice.login(login);
		
	}
	
	@PutMapping("product/add/{mid}")
	public ResponseStructure<Merchant> saveproduct(@RequestBody Product product ,@PathVariable String mid)
	{
		return merchantservice.saveproduct(mid,product);
		
	}
	@GetMapping("product/fetch/{mid}")
	public ResponseStructure<List<Product>> fetchproduct(@PathVariable String mid) throws AllException{
		return merchantservice.fetchproduct(mid);
	}
	@PutMapping("product/edit/{mid}")
	public ResponseStructure<Product> editproduct(@RequestBody Product product, @PathVariable String mid )
	{
		return merchantservice.updateproduct(product,mid);
	}
	@DeleteMapping("product/delete/{mid}/{pid}")
	public ResponseStructure<Product> deleteproduct(@PathVariable int pid, @PathVariable String mid ){
		return merchantservice.deleteproduct(mid,pid);
	}
}
