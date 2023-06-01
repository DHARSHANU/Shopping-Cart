package org.jsp.Spring_boot_project.service;

import java.util.List;
import java.util.Random;

import org.jsp.Spring_boot_project.dao.MechantDao;
import org.jsp.Spring_boot_project.dao.ProductDao;
import org.jsp.Spring_boot_project.dto.Merchant;
import org.jsp.Spring_boot_project.dto.Product;
import org.jsp.Spring_boot_project.exception.AllException;
import org.jsp.Spring_boot_project.helper.Login;
import org.jsp.Spring_boot_project.helper.ResponseStructure;
import org.jsp.Spring_boot_project.helper.VerficationEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class Merchantservice {

	@Autowired
	VerficationEmailSender emailSender;

	@Autowired
	MechantDao dao;

	@Autowired
	ProductDao dao2;

	public ResponseStructure<Merchant> signup(Merchant merchant) {

		ResponseStructure<Merchant> structure = new ResponseStructure<>();

		merchant.setOtp(new Random().nextInt(100000, 999999));

		emailSender.sendemail(merchant);

		structure.setMessage("Verfication mail sent verfiy OTP to create account");
		structure.setStatuscode(HttpStatus.PROCESSING.value());
		structure.setData(dao.save(merchant));
		return structure;
	}

	public ResponseStructure<Merchant> login(Login login) throws AllException {
		// TODO Auto-generated method stub
		ResponseStructure<Merchant> structure = new ResponseStructure<>();

		Merchant merchant = dao.find(login.getId());
		if (merchant == null) {
			throw new AllException("invalid id");
		} else {
			if (merchant.getPassword().equals(login.getPassword())) {
				if (merchant.isStatus()) {

					structure.setData(merchant);
					structure.setMessage("login succes");
					structure.setStatuscode(HttpStatus.ACCEPTED.value());
				} else {

					throw new AllException("virfiy otp first");

				}

			} else {
				throw new AllException("invalid password");
			}
		}
		return structure;
	}

	public ResponseStructure<Merchant> verfiy(String id, int otp) throws AllException {
		// TODO Auto-generated method stub
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Merchant merchant = dao.find(id);
		if (merchant.getOtp() == otp) {
			merchant.setStatus(true);
			structure.setMessage("account Created Successfully");
			structure.setStatuscode(HttpStatus.CREATED.value());
			structure.setData(dao.save(merchant));
		} else {
			throw new AllException("OTP Mismatch");
		}
		return structure;
	}

	public ResponseStructure<Merchant> saveproduct(String mid, Product product) {
		// TODO Auto-generated method stub
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Merchant merchant = dao.find(mid);
		

		product.setPname(merchant.getName()+""+product.getPname());
		product.setMerchant(merchant);

		List<Product> list = merchant.getProducts();
		list.add(product);

		structure.setMessage("product added successfully");
		structure.setData(dao.save(merchant));
		structure.setStatuscode(HttpStatus.CREATED.value());
		return structure;
	}

	public ResponseStructure<List<Product>> fetchproduct(String mid) throws AllException {
		// TODO Auto-generated method stub
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();

		Merchant merchant = dao.find(mid);

		if (merchant.getProducts().isEmpty()) {
			throw new AllException("No products Found");
		} else {
			structure.setMessage("Data Found");
			structure.setStatuscode(HttpStatus.FOUND.value());
			structure.setData(merchant.getProducts());
			return structure;
		}
	}

	public ResponseStructure<Product> updateproduct(Product product, String mid) {
		// TODO Auto-generated method stub
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Merchant merchant = dao.find(mid);
		product.setMerchant(merchant);

		structure.setData(dao2.save(product));
		structure.setMessage("updated successfully");
		structure.setStatuscode(HttpStatus.ACCEPTED.value());

		return structure;
	}

	public ResponseStructure<Product> deleteproduct(String mid, int pid) {

		Merchant merchant = dao.find(mid);
		Product product = dao2.find(pid);
		merchant.getProducts().remove(product);
		dao.save(merchant);
		dao2.deleteproduct(pid);

		ResponseStructure<Product> structure = new ResponseStructure<>();
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		structure.setMessage("Deleted successfully");
		structure.setData(product);
		return structure;

	}

}
