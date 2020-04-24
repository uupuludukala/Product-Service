package com.coolbook.erp.rest.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.coolbook.erp.entity.BranchEntity;
import com.coolbook.erp.model.BranchGet;

public abstract class AbstractBranchResourceAssembler extends ResourceAssemblerSupport<BranchEntity, BranchGet>{
	protected String requestURI;
	
	public AbstractBranchResourceAssembler() {
		super(BranchEntity.class, BranchGet.class);
	}

	@Override
	public BranchGet toResource(BranchEntity branchEntity) {
		return createBranchJson(branchEntity);
	}
	
	private BranchGet createBranchJson(BranchEntity branchEntity) {
		BranchGet branchGet=new BranchGet();
		branchGet.setBranch_id(branchEntity.getId());
		branchGet.setAddressLine1(branchEntity.getAddressLine1());
		branchGet.setAddressLine2(branchEntity.getAddressLine2());
		branchGet.setAddressLine3(branchEntity.getAddressLine3());
		branchGet.setBranchCode(branchEntity.getBranchCode());
		branchGet.setBranchName(branchEntity.getBranchName());
		branchGet.setCompanyId(branchEntity.getCompany().getId());
		branchGet.setContactNumber(branchEntity.getConatactNumber());
		return branchGet;
	}
	
	protected abstract String getBranchSelfLink(String id);
}
