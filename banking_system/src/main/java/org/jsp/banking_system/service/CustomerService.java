package org.jsp.banking_system.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.jsp.banking_system.dto.BankAccount;
import org.jsp.banking_system.dto.Customer;
import org.jsp.banking_system.dto.Login;
import org.jsp.banking_system.exception.MyException;
import org.jsp.banking_system.helper.Mailverification;
import org.jsp.banking_system.helper.ResponseStructure;
import org.jsp.banking_system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	Mailverification mailverification;
	
	@Autowired
	BankAccount account;

	public ResponseStructure<Customer> saveCustomer(Customer customer) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<>();

		// to count age automatically
		int age = Period.between(customer.getDob().toLocalDate(), LocalDate.now()).getYears();
		customer.setAge(age);

		if (age < 18) {
			responseStructure.setMessage("customer age should be greater than 18");
			responseStructure.setCode(HttpStatus.CONFLICT.value());
			responseStructure.setData(customer);
		} else {
			Random random = new Random();
			int otp = random.nextInt(100000, 999999);
			customer.setOtp(otp);

			mailverification.sendMail(customer);

			responseStructure.setMessage("OTP has send to mail to verification");
			responseStructure.setCode(HttpStatus.PROCESSING.value());
			responseStructure.setData(customerRepository.save(customer));

		}

		return responseStructure;
	}

	public ResponseStructure<Customer> verify(int cust_id, int otp) throws MyException {
		ResponseStructure<Customer> structure = new ResponseStructure<>();
		Optional<Customer> optional = customerRepository.findById(cust_id);
		if (optional.isEmpty())// create the excption class becaiuse userexception
		{
			throw new MyException("check id and try again");
		} else {
			Customer customer = optional.get();
			if (customer.getOtp() == otp) {
				structure.setCode(HttpStatus.CREATED.value());
				structure.setMessage("account created successfully");
				customer.setStatus(true);
				structure.setData(customerRepository.save(customer));

			} else {
				throw new MyException("otp missatch");
			}
		}

		return structure;

	}

	public ResponseStructure<Customer> login(Login login) throws MyException 
	{
	ResponseStructure<Customer> structure=new ResponseStructure<>();
			 Optional<Customer>	optional=customerRepository.findById(login.getId());
				if(optional.isEmpty())
				{
					throw new MyException("invalid customer id");
				}
				else
				{
				Customer customer=optional .get();
				if(customer.getPassword().equals(login.getPassword()))
				{
					if(customer.isStatus())
					{
						structure.setCode(HttpStatus.ACCEPTED.value());
						structure.setMessage("login suuccess");
						structure.setData(customer);
				    }
					else 
				    {
					throw new MyException("veify your email first");
				   }
				}
				else
				{
					throw new MyException("invalid password");
				}
	}
				
				return structure;
}
	public ResponseStructure<Customer> createAccount( int cust_id,  String type) throws MyException{
	
		ResponseStructure<Customer> structure=new ResponseStructure<>();
		Optional<Customer>	optional=customerRepository.findById(cust_id);
		if(optional.isEmpty())
		{
			throw new MyException("invalid customer id");
		}
		else
		{
		Customer customer=optional.get();
		List<BankAccount > list=customer.getAccounts();
		
		boolean flag=true;
		for(BankAccount account:list) {
			if(account.getType().equals(type))
			{
			flag=false;
			break;
			}
		}
		if(!flag)
		{
			throw new MyException(type +"account already exists");
		}
		else {
		account.setType(type);
		if(type.equals("savings"))
		{
			account.setBankLimit(5000);
		}else{
			account.setBankLimit(10000);
		}
		list.add(account);
		customer.setAccounts(list);
		structure.setCode(HttpStatus.ACCEPTED.value());
		structure.setData(customerRepository.save(customer));
		structure.setMessage("account created sucessfully");

	}
		}
		return structure;
	}

	
}
	
	

