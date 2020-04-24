package com.coolbook.erp.rest.assembler;

import org.springframework.stereotype.Component;

import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.model.CompanyGet;
import com.coolbook.erp.model.CompanyPost;

@Component
public class CompanyAssembler {

	public CompanyGet essembleCompanyGet(CompanyEntity companyEntity) {
		CompanyGet companyGet = new CompanyGet();
		companyGet.setAddressLine1(companyEntity.getAddressLine1());
		companyGet.setAddressLine2(companyEntity.getAddressLine2());
		companyGet.setAddressLine3(companyEntity.getAddressLine3());
		companyGet.setCompanyCode(companyEntity.getCompanyCode());
		companyGet.setCompanyName(companyEntity.getCompanyName());
		companyGet.setContactNumber(companyEntity.getConatactNumber());
		companyGet.setCompany_id(companyEntity.getId());
		return companyGet;
	}

	public CompanyEntity essembleCompanyEntity(CompanyPost companyPost) {
		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setAddressLine1(companyPost.getAddressLine1());
		companyEntity.setAddressLine2(companyPost.getAddressLine2());
		companyEntity.setAddressLine3(companyPost.getAddressLine3());
		companyEntity.setCompanyCode(companyPost.getCompanyCode());
		companyEntity.setCompanyName(companyPost.getCompanyName());
		companyEntity.setConatactNumber(companyPost.getContactNumber());
		return companyEntity;

	}

}
