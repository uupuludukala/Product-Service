package com.coolbook.erp.rest.assembler;

import org.springframework.stereotype.Component;

import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.model.CompanyGet;
import com.coolbook.model.CompanyPost;

@Component
public class CompanyAssembler {

	public CompanyGet essembleCompanyGet(CompanyEntity companyEntity) {
		CompanyGet companyGet = new CompanyGet();
		companyGet.setAdress1(companyEntity.getAdress1());
		companyGet.setAdress2(companyEntity.getAdress2());
		companyGet.setAdress3(companyEntity.getAdress3());
		companyGet.setCompanyCode(companyEntity.getCompanyCode());
		companyGet.setCompanyName(companyEntity.getCompanyName());
		companyGet.setConatactNumber(companyEntity.getConatactNumber());
		companyGet.setCompany_id(companyEntity.getId());
		return companyGet;
	}

	public CompanyEntity essembleCompanyentity(CompanyPost companyPost) {
		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setAdress1(companyPost.getAdress1());
		companyEntity.setAdress2(companyPost.getAdress2());
		companyEntity.setAdress3(companyPost.getAdress3());
		companyEntity.setCompanyCode(companyPost.getCompanyCode());
		companyEntity.setCompanyName(companyPost.getCompanyName());
		companyEntity.setConatactNumber(companyPost.getConatactNumber());
		return companyEntity;

	}

}
