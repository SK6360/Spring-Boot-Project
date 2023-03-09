package org.jsp.banking_system.controller;

import org.jsp.banking_system.dto.Management;
import org.jsp.banking_system.helper.ResponseStructure;
import org.jsp.banking_system.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management")
public class ManagementController
{
	@Autowired
ManagementService service;
	
	@PostMapping("add")
	public  ResponseStructure<Management>save (@RequestBody Management management) {
		return service.save(management);
	}
	//management login
	
}
