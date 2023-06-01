

package org.jsp.Spring_boot_project.helper;

import org.jsp.Spring_boot_project.dto.Customer;
import org.jsp.Spring_boot_project.dto.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class VerficationEmailSender {
	@Autowired
	JavaMailSender javaMailSender ;

	public void sendemail(Merchant merchant) {
	MimeMessage message =javaMailSender.createMimeMessage();
		
	MimeMessageHelper helper = new  MimeMessageHelper(message);
	
	try {
		helper.setFrom("damucool1999@gmail.com");
		helper.setTo(merchant.getEmail());
		helper.setSubject("verfication mail");
		helper.setText("Hello"+merchant.getName()+" "+"your OTP for mail verfication is "+merchant.getOtp());
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	javaMailSender.send(message);
	}

	public void sendemail(Customer customer ) {
		MimeMessage message =javaMailSender.createMimeMessage();
			
		MimeMessageHelper helper = new  MimeMessageHelper(message);
		
		try {
			helper.setFrom("damucool1999@gmail.com");
			helper.setTo(customer.getEmail());
			helper.setSubject("verfication mail");
			helper.setText("Hello"+customer.getName()+" "+"your OTP for mail verfication is "+customer.getOtp());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		javaMailSender.send(message);
		}
	
	
}
