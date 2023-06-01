
package org.jsp.Spring_boot_project.controller;

import java.util.List;

import org.jsp.Spring_boot_project.dto.Cart;
import org.jsp.Spring_boot_project.dto.Customer;
import org.jsp.Spring_boot_project.dto.Product;
import org.jsp.Spring_boot_project.dto.ShoppingOrder;
import org.jsp.Spring_boot_project.dto.Customer;
import org.jsp.Spring_boot_project.exception.AllException;
import org.jsp.Spring_boot_project.helper.Login;
import org.jsp.Spring_boot_project.helper.ResponseStructure;
import org.jsp.Spring_boot_project.service.Customerservice;
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
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	Customerservice customerservice;

	@PostMapping("signup")
	public ResponseStructure<Customer> signup(@RequestBody Customer customer) {
		return customerservice.signup(customer);

	}

	@PutMapping("verify/{id}/{otp}")
	public ResponseStructure<Customer> verfiy(@PathVariable String id, @PathVariable int otp) throws AllException {
		return customerservice.verfiy(id, otp);
	}

	@PostMapping("login")
	public ResponseStructure<Customer> login(@RequestBody Login login) throws AllException

	{
		return customerservice.login(login);

	}

	@GetMapping("product/fetch")
	public ResponseStructure<List<Product>> fetch() throws AllException {

		return customerservice.fetch();
	}

	@PutMapping("cart/add/{cid}/{pid}")
	public ResponseStructure<Cart> addtocart(@PathVariable String cid, @PathVariable int pid) {
		return customerservice.addtocart(cid, pid);
	}

	@DeleteMapping("cart/remove/{cid}/{pid}")
	public ResponseStructure<Cart> removefromcart(@PathVariable String cid, @PathVariable int pid) throws AllException {
		return customerservice.removefromcart(cid, pid);
	}

	@PutMapping("order/place/{cid}")
	public ResponseStructure<List<ShoppingOrder>> placeorder(@PathVariable String cid) throws AllException {

		return customerservice.placeorder(cid);
	}

	@PutMapping("wallet/{cid}/{amt}")
	public ResponseStructure<Customer> addmoney(@PathVariable String cid, @PathVariable double amt) {
		return customerservice.addmoney(cid, amt);
	}

	@GetMapping("order/fetch/{cid}")
	public ResponseStructure<List<ShoppingOrder>> viewallorder(@PathVariable String cid) throws AllException {

		return customerservice.viewallorder(cid);
	}

	@PutMapping("review/{pid}")
	public ResponseStructure<Product> review(@PathVariable int pid) {
		return customerservice.review(pid);
	}
}