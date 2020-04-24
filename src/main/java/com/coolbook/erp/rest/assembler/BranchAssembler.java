package com.coolbook.erp.rest.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coolbook.erp.entity.BranchEntity;
import com.coolbook.erp.model.BranchGet;
import com.coolbook.erp.model.BranchPost;
import com.coolbook.erp.repository.CompanyRepository;

@Component
public class BranchAssembler {
	
	
	CompanyRepository companyRepository;
	
	@Autowired
	BranchAssembler(CompanyRepository companyRepository){
		this.companyRepository=companyRepository;
	}
	
	
	
	 public BranchEntity essembleBranchEntity(BranchPost branchPost) {
		 BranchEntity  branchEntity=new BranchEntity ();
		 branchEntity.setBranchCode(branchPost.getBranchCode());
		 branchEntity.setBranchName(branchPost.getBranchName());
		 branchEntity.setAddressLine1(branchPost.getAddressLine1());
		 branchEntity.setAddressLine2(branchPost.getAddressLine2());
		 branchEntity.setAddressLine3(branchPost.getAddressLine3());
		 branchEntity.setCompany(companyRepository.getOne(branchPost.getCompanyId()));
		 branchEntity.setConatactNumber(branchPost.getContactNumber());
		 return branchEntity;
	 }
	 
	 public BranchGet essembleBranchGet(BranchEntity branchEntity) {
		 BranchGet branchGet=new BranchGet();
		 branchGet.setBranch_id(branchEntity.getId());
		 branchGet.setBranchCode(branchEntity.getBranchCode());
		 branchGet.setBranchName(branchEntity.getBranchName());
		 branchGet.setAddressLine1(branchEntity.getAddressLine1());
		 branchGet.setAddressLine2(branchEntity.getAddressLine2());
		 branchGet.setAddressLine3(branchEntity.getAddressLine3());
		 branchGet.setCompanyId(branchEntity.getCompany().getId());
		 branchGet.setContactNumber(branchEntity.getConatactNumber());
		 return branchGet;		 
	 }
}
