package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.common.enums.StatusEnum;
import com.coolbook.erp.model.BranchGet;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.coolbook.erp.entity.BranchEntity;

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
		branchGet.setContactNumber(branchEntity.getContactNumber());
        branchGet.setStatus(StatusEnum.getByCode(branchEntity.getStatus()));
		if(branchEntity.getCompany()!=null) {
            branchGet.setCompanyId(branchEntity.getCompany().getId());
            branchGet.setCompanyCode(branchEntity.getCompany().getCompanyCode());
            branchGet.setCompanyName(branchEntity.getCompany().getCompanyName());
        }
		return branchGet;
	}
	
	protected abstract String getBranchSelfLink(String id);
}
