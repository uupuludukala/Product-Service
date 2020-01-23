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
		 branchEntity.setAddress1(branchPost.getAddress1());
		 branchEntity.setAddress2(branchPost.getAddress2());
		 branchEntity.setAddress3(branchPost.getAddress3());
		 branchEntity.setCompany(companyRepository.getOne(branchPost.getCompanyId()));
		 branchEntity.setConatactNumber(branchPost.getConatactNumber());
		 return branchEntity;
	 }
	 
	 public BranchGet essembleBranchGet(BranchEntity branchEntity) {
		 BranchGet branchGet=new BranchGet();
		 branchGet.setBranch_id(branchEntity.getId());
		 branchGet.setBranchCode(branchEntity.getBranchCode());
		 branchGet.setAddress1(branchEntity.getAddress1());
		 branchGet.setAddress2(branchEntity.getAddress2());
		 branchGet.setAddress3(branchEntity.getAddress3());
		 branchGet.setCompanyId(branchEntity.getCompany().getId());
		 branchGet.setConatactNumber(branchEntity.getConatactNumber());
		 return branchGet;		 
	 }
}
