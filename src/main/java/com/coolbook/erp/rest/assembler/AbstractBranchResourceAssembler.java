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
		branchGet.setAddress1(branchEntity.getAddress1());
		branchGet.setAddress2(branchEntity.getAddress2());
		branchGet.setAddress3(branchEntity.getAddress3());
		branchGet.setBranchCode(branchEntity.getBranchCode());
		branchGet.setCompanyId(branchEntity.getCompany().getId());
		branchGet.setConatactNumber(branchEntity.getConatactNumber());
		return branchGet;
	}
	
	protected abstract String getBranchSelfLink(String id);
}
