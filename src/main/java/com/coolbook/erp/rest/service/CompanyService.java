package com.coolbook.erp.rest.service;

import com.coolbook.erp.rest.searchCriteria.CompanyCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.repository.CompanyRepository;
import com.coolbook.erp.repository.specs.CompanySearchSpecification;
import com.coolbook.erp.repository.specs.CompanySpecification;

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
	
	public void updateCompany(CompanyEntity company, long id) {
		company.setId(id);
		this.companyRepository.save(company);
	}

	public void deleteCompany(long id) {

		this.companyRepository.delete(id);
	}
	public CompanyEntity getCompanyById(long id) {
		return this.companyRepository.getOne(id);
	}
	public Page<CompanyEntity> getAllCompany(Pageable page, CompanyCriteria criteria){
		CompanySpecification specification=new CompanySpecification(criteria);
		return this.companyRepository.findAll(specification,page);
	}
	
	public Page<CompanyEntity> searchCompany(Pageable page, String searchValue) {
		CompanySearchSpecification specification = new CompanySearchSpecification(searchValue);
		return this.companyRepository.findAll(specification, page);
	}
}
