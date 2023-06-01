package org.jsp.Spring_boot_project.controller;

import java.security.PublicKey;
import java.util.List;

import org.jsp.Spring_boot_project.dto.Product;
import org.jsp.Spring_boot_project.exception.AllException;
import org.jsp.Spring_boot_project.helper.Login;
import org.jsp.Spring_boot_project.helper.ResponseStructure;
import org.jsp.Spring_boot_project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService ;
	
	@PostMapping("login")
	public  ResponseStructure<Login> login(@RequestBody Login login ) throws AllException{
		return adminService.login(login);
	}
	@GetMapping("product/fetch")
	public ResponseStructure<List<Product>> fetchproduct() throws AllException{
		return adminService.fetchproduct();
	}
	@PutMapping("product/status/{pid}")
	public ResponseStructure<Product> changestatus(@ PathVariable int pid){
		return adminService.changestatus(pid);
	}
}
