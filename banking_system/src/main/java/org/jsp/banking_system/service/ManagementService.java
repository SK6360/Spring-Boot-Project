package org.jsp.banking_system.service;

import org.jsp.banking_system.dto.Management;
import org.jsp.banking_system.helper.ResponseStructure;
import org.jsp.banking_system.repository.ManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ManagementService 
{
	@Autowired
	ManagementRepository repository;
public ResponseStructure<Management> save (Management management) {
	ResponseStructure<Management> structure=new ResponseStructure<>();
	structure.setCode(HttpStatus.CREATED.value());
	structure.setMessage("account created succefully");
	structure.setData(repository.save(management));
	return structure;
}
}
