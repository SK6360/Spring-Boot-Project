package org.jsp.banking_system.helper;

import org.jsp.banking_system.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class Mailverification {
	@Autowired
JavaMailSender javaMailSender;
	public void  sendMail(Customer customer) {
		MimeMessage mimemessage=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimemessage);
		try {
			helper.setFrom("ayushgowdapubg@gmail.com");
			helper.setTo(customer.getEmail());
			helper.setText("your otp for email verification is "+customer.getOtp());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		javaMailSender.send(mimemessage);
	}
}
