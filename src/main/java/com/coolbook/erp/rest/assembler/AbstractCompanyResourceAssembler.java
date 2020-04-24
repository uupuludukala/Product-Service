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
		companyGet.setAddressLine1(companyEntity.getAddressLine1());
		companyGet.setAddressLine2(companyEntity.getAddressLine2());
		companyGet.setAddressLine3(companyEntity.getAddressLine3());
		companyGet.setCompanyCode(companyEntity.getCompanyCode());
		companyGet.setCompanyName(companyEntity.getCompanyName());
		companyGet.setContactNumber(companyEntity.getConatactNumber());
		companyGet.setCompany_id(companyEntity.getId());
		return companyGet;
	}
	
	protected abstract String getSelfLink(String id);
}
