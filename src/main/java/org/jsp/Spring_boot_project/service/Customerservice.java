package org.jsp.Spring_boot_project.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.jsp.Spring_boot_project.dao.Customerdao;
import org.jsp.Spring_boot_project.dao.MechantDao;
import org.jsp.Spring_boot_project.dao.ProductDao;
import org.jsp.Spring_boot_project.dto.Cart;
import org.jsp.Spring_boot_project.dto.Customer;
import org.jsp.Spring_boot_project.dto.Item;
import org.jsp.Spring_boot_project.dto.Merchant;
import org.jsp.Spring_boot_project.dto.Product;
import org.jsp.Spring_boot_project.dto.ShoppingOrder;
import org.jsp.Spring_boot_project.exception.AllException;
import org.jsp.Spring_boot_project.dto.Customer;
import org.jsp.Spring_boot_project.helper.Login;
import org.jsp.Spring_boot_project.helper.ResponseStructure;
import org.jsp.Spring_boot_project.helper.VerficationEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Customerservice {

	@Autowired
	MechantDao mechantDao;
	
	@Autowired
	VerficationEmailSender emailSender;

	@Autowired
	Customerdao customerdao;

	@Autowired
	ProductDao dao;

	@Autowired
	Cart cart;

	@Autowired
	Item item;

	@Autowired
	ShoppingOrder order;

	public ResponseStructure<Customer> signup(Customer customer) {

		ResponseStructure<Customer> structure = new ResponseStructure<>();

		customer.setOtp(new Random().nextInt(100000, 999999));

		emailSender.sendemail(customer);

		structure.setMessage("Verfication mail sent verfiy OTP to create account");
		structure.setStatuscode(HttpStatus.PROCESSING.value());
		structure.setData(customerdao.save(customer));
		return structure;
	}

	public ResponseStructure<Customer> login(Login login) throws AllException {
		// TODO Auto-generated method stub
		ResponseStructure<Customer> structure = new ResponseStructure<>();

		Customer customer = customerdao.find(login.getId());
		if (customer == null) {
			throw new AllException("invalid id");
		} else {
			if (customer.getPassword().equals(login.getPassword())) {
				if (customer.isStatus()) {

					structure.setData(customer);
					structure.setMessage("login succes");
					structure.setStatuscode(HttpStatus.ACCEPTED.value());
				} else {

					throw new AllException("virfiy otp first");

				}
				structure.setData(customer);
				structure.setMessage("login succes");
				structure.setStatuscode(HttpStatus.ACCEPTED.value());
			} else {
				throw new AllException("invalid password");
			}
		}
		return structure;
	}

	public ResponseStructure<Customer> verfiy(String id, int otp) throws AllException {
		// TODO Auto-generated method stub
		ResponseStructure<Customer> structure = new ResponseStructure<>();
		Customer customer = (Customer) customerdao.find(id);
		if (customer.getOtp() == otp) {
			customer.setStatus(true);
			structure.setMessage("account Created Successfully");
			structure.setStatuscode(HttpStatus.CREATED.value());
			structure.setData(customerdao.save(customer));
		} else {
			throw new AllException("OTP Mismatch");
		}
		return structure;
	}

	public ResponseStructure<List<Product>> fetch() throws AllException {
		// TODO Auto-generated method stub
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();

		List<Product> products = dao.fetchcustomerproducts();
		if (products.isEmpty()) {
			throw new AllException("data not found");
		} else {
			structure.setMessage("found successfully");
			structure.setStatuscode(HttpStatus.FOUND.value());
			structure.setData(products);

			return structure;
		}
	}

	public ResponseStructure<Cart> addtocart(String cid, int pid) {
		// TODO Auto-generated method stub
		ResponseStructure<Cart> structure = new ResponseStructure<>();

		Customer customer = customerdao.find(cid);
		Product product = dao.find(pid);

		Cart cart = customer.getCart();

		if (cart == null) {
			cart = this.cart;

		}

		List<Item> items = cart.getItems();
		if (items == null) {
			items = new ArrayList<Item>();
		}
		if (items.isEmpty()) {
			item.setName(product.getPname());
			item.setPrice(product.getPrice());
			item.setQuantity(1);
			items.add(item);
		} else {
			boolean flag = false;
			for (Item item : items) {
				if (item.getName().equals(product.getPname())) {
					item.setQuantity(item.getQuantity() + 1);
					item.setPrice(item.getPrice() + product.getPrice());
					flag = false;
					break;
				} else {
					flag = true;
				}
			}
			if (flag) {
				item.setName(product.getPname());
				item.setPrice(product.getPrice());
				item.setQuantity(1);
				items.add(item);
			}
		}
		cart.setItems(items);
		customer.setCart(cart);

		structure.setMessage("Added to cart");
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		structure.setData(customerdao.save(customer).getCart());

		return structure;
	}

	public ResponseStructure<Cart> removefromcart(String cid, int pid) throws AllException {
		// TODO Auto-generated method stub
		ResponseStructure<Cart> structure = new ResponseStructure<>();

		Customer customer = customerdao.find(cid);
		Product product = dao.find(pid);

		Cart cart = customer.getCart();

		List<Item> items = cart.getItems();

		if (items.isEmpty()) {
			throw new AllException("no item in cart");
		}

		else {
			Item item2 = null;
			for (Item item : items) {

				if (item.getName().equals(product.getPname())) {
					if (item.getQuantity() > 1) {
						item.setQuantity(item.getQuantity() - 1);
						item.setPrice(item.getPrice() - product.getPrice());

					} else {
						item2 = item;
					}
				}
			}
			if (item2 != null) {
				items.remove(item2);

			}
		}
		cart.setItems(items);
		customer.setCart(cart);

		structure.setMessage("Removed from cart");
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		structure.setData(customerdao.save(customer).getCart());

		return structure;
	}

	public ResponseStructure<List<ShoppingOrder>> placeorder(String cid) throws AllException {
		// TODO Auto-generated method stub
		ResponseStructure<List<ShoppingOrder>> structure = new ResponseStructure<>();

		Customer customer = customerdao.find(cid);

		Cart cart = customer.getCart();

		List<Item> items = cart.getItems();

		double price = 0;
		for (Item item : items) {
			price = price + item.getPrice();
		}

		order.setDateandtime(LocalDateTime.now());
		order.setItems(items);
		order.setOrderprice(price);

		List<ShoppingOrder> list = customer.getOrders();
		if (list == null) {
			list = new ArrayList<>();

		}

		list.add(order);
		customer.setOrders(list);

		if (customer.getWallet() < price) {
			throw new AllException("insufficent fund to place order");

		} else {
			for(Item item : order.getItems()) {
			Product product = dao.find(item.getName());
			Merchant merchant= product.getMerchant();
			merchant.setWallet(merchant.getWallet()+item.getPrice());
			
			mechantDao.save(merchant);
			}
			
			customer.setWallet(customer.getWallet()-price);
			customer.setCart(null);
			structure.setMessage("placed order");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			structure.setData(customerdao.save(customer).getOrders());
		}

		return structure;
	}

	public ResponseStructure<Customer> addmoney(String cid, double amt) {
		// TODO Auto-generated method stub
		ResponseStructure<Customer> structure = new ResponseStructure<>();
		Customer customer = customerdao.find(cid);
		customer.setWallet(customer.getWallet() + amt);

		structure.setMessage("Added Money");
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		structure.setData(customerdao.save(customer));

		return structure;
	}

	public ResponseStructure<List<ShoppingOrder>> viewallorder(String cid) throws AllException {
		// TODO Auto-generated method stub
		ResponseStructure<List<ShoppingOrder>> structure = new ResponseStructure<>();
		Customer customer = customerdao.find(cid);
		List<ShoppingOrder> orders = customer.getOrders();
		if (orders.isEmpty()) {
			throw new AllException(" no order found");

		} else {
			structure.setMessage("orders found");
			structure.setStatuscode(HttpStatus.FOUND.value());
			structure.setData(orders);
		}

		return structure;
	}

	public ResponseStructure<Product> review(int pid) {
		// TODO Auto-generated method stub
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Product product = dao.find(pid);

		product.setBadreview(product.getBadreview()+1);
		
		if (product.getBadreview()>3) {
			product.setStatus(false);
			
		}
		structure.setMessage("Rreview updated");
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		structure.setData(dao.save(product));
		
		return structure;
	}

}
