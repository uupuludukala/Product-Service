package com.coolbook.erp.rest.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.model.CompanyGet;

public abstract class AbstractCompanyResourceAssembler extends ResourceAssemblerSupport<CompanyEntity, CompanyGet>{
	protected String requestURI;
	
	public AbstractCompanyResourceAssembler() {
		super(CompanyEntity.class, CompanyGet.class);
	}

	@Override
	public CompanyGet toResource(CompanyEntity companyEntity) {
		return createCompanyJson(companyEntity);
	}
	
	private CompanyGet createCompanyJson(CompanyEntity companyEntity) {
		CompanyGet companyGet=new CompanyGet();
		companyGet.setAdress1(companyEntity.getAdress1());
		companyGet.setAdress2(companyEntity.getAdress2());
		companyGet.setAdress3(companyEntity.getAdress3());
		companyGet.setCompanyCode(companyEntity.getCompanyCode());
		companyGet.setCompanyName(companyEntity.getCompanyName());
		companyGet.setConatactNumber(companyEntity.getConatactNumber());
		companyGet.setCompany_id(companyEntity.getId());
		return companyGet;
	}
	
	protected abstract String getSelfLink(String id);
}
