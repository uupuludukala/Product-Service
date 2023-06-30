package com.coolbook.erp.rest.controller;

import com.coolbook.erp.common.enums.StatusEnum;
import com.coolbook.erp.rest.service.BranchService;
import com.coolbook.erp.rest.service.CompanyService;
import com.coolbook.erp.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coolbook.erp.entity.BranchEntity;
import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.entity.UserEntity;

@RestController
public class InitialSetupController {

	@Autowired
    BranchService branchService;
	
	@Autowired
    CompanyService companyService;
	
	@Autowired
    UserService userService;
	
	@RequestMapping(value = "initialSetup", method = RequestMethod.POST)
	public ResponseEntity<Void> setup() {
		CompanyEntity company=new CompanyEntity();		
		
		company.setAddressLine1("Main Street");
		company.setAddressLine2("Athura");
		company.setAddressLine3("Bulathsinhala");
		company.setCompanyCode("FNC");
		company.setCompanyName("Furnico");
		company.setContactNumber("0342283022");
		company.setStatus(StatusEnum.ACTIVE.getCode());
		long comanyId = companyService.saveCompany(company);
		
		BranchEntity  branch=new BranchEntity();
		branch.setAddressLine1("Main Street");
		branch.setAddressLine2("Athura");
		branch.setAddressLine3("Bulathsinhala");
		branch.setBranchCode("FNC");
		branch.setBranchName("Furnico");
		branch.setContactNumber("0342283022");
		branch.setCompany(company);
		branch.setStatus(StatusEnum.ACTIVE.getCode());
		long branchid=branchService.saveBranch(branch);
		
		UserEntity user=new UserEntity();
		user.setPassword("$2a$10$2Rezb8ejYQPNzNF4muBSxeHiE9Y9ejWZH5kozJbcVQsc1dysKTQmC");
		user.setUserName("furnico");
		user.setBranch(branch);
		user.setStatus(StatusEnum.ACTIVE.getCode());
		userService.saveUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
}
