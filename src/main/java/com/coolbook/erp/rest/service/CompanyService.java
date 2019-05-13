package com.coolbook.erp.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.repository.CompanyRepository;
import com.coolbook.erp.repository.specs.CompanySpecification;
import com.coolbook.erp.rest.searchCriteria.CompanyCriteria;

@Service
public class CompanyService {
CompanyRepository companyRepository;
	
	@Autowired
	CompanyService(CompanyRepository companyRepository){
		this.companyRepository=companyRepository;
	}
	public long saveCompany(CompanyEntity company) {
		return this.companyRepository.save(company).getId();
	}
	public CompanyEntity getCompanyById(long id) {
		return this.companyRepository.getOne(id);
	}
	public Page<CompanyEntity> getAllCompany(Pageable page,CompanyCriteria criteria){
		CompanySpecification specification=new CompanySpecification(criteria);
		return this.companyRepository.findAll(specification,page);
	}
}
