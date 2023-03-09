package org.jsp.banking_system.controller;

import org.jsp.banking_system.dto.BankAccount;
import org.jsp.banking_system.dto.Customer;
import org.jsp.banking_system.dto.Login;
import org.jsp.banking_system.exception.MyException;
import org.jsp.banking_system.helper.ResponseStructure;
import org.jsp.banking_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@PostMapping("add")
	public ResponseStructure<Customer> saveCustomer(@RequestBody Customer customer){
		return customerService.saveCustomer(customer);
	}
	//to verify the verify 
	@PutMapping("otp/{cust_id}/{otp}")//11212/12555464
	public ResponseStructure<Customer> otpVerify(@PathVariable int cust_id, @PathVariable int otp) throws MyException
	{
		return customerService.verify(cust_id,otp);
	}
	//customer login
	@PostMapping("login")
	public ResponseStructure<Customer> login(@RequestBody Login login) throws MyException
	{
		return customerService.login(login);
	}
	@PostMapping("account/{cust_id}/{type}")
	public ResponseStructure<Customer> createAccount(@PathVariable int cust_id, @PathVariable String type) throws MyException
	{
		return customerService.createAccount(cust_id,type);
	}

}
