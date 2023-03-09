package org.jsp.banking_system.repository;

import java.util.Optional;

import org.jsp.banking_system.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	//Optional<Customer> findById(String password);

}
