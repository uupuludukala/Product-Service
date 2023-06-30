package com.coolbook.erp.rest.assembler;


import com.coolbook.erp.entity.VendorAccountEntity;
import com.coolbook.erp.entity.VendorEntity;
import com.coolbook.erp.model.VendorAccountGet;
import com.coolbook.erp.model.VendorGet;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.HashSet;
import java.util.Set;


public abstract class AbstractVendorResourceAssembler extends ResourceAssemblerSupport<VendorEntity, VendorGet> {
    protected String requestURI;

    public AbstractVendorResourceAssembler() {
        super(VendorEntity.class, VendorGet.class);
    }

    @Override
    public VendorGet toResource(VendorEntity vendorEntity) {
        return createVendorJson(vendorEntity);
    }

    private VendorGet createVendorJson(VendorEntity vendorEntity) {
        VendorGet vendorGet = new VendorGet();
        vendorGet.setVendor_Id(vendorEntity.getId());
        vendorGet.setVendorName(vendorEntity.getVendorName());
        vendorGet.setAddressLine1(vendorEntity.getAddressLine1());
        vendorGet.setAddressLine2(vendorEntity.getAddressLine2());
        vendorGet.setAddressLine3(vendorEntity.getAddressLine3());
        vendorGet.setContactPerson(vendorEntity.getContactPerson());
        vendorGet.setPhone(vendorEntity.getPhone());
        vendorGet.setMobile(vendorEntity.getMobile());
        vendorGet.setEmail(vendorEntity.getEmail());
        vendorGet.setVendorAccounts(assembleVendorAccountGet(vendorEntity.getVendorAccounts()));
        return vendorGet;
    }
    
    private Set<VendorAccountGet> assembleVendorAccountGet(Set<VendorAccountEntity> vendorAccounts){
        Set<VendorAccountGet> vendorAccountsGet= new HashSet<>();
        for(VendorAccountEntity vendorAccountEntity:vendorAccounts){
            vendorAccountsGet.add(new VendorAccountGet(vendorAccountEntity.getId(),
                    vendorAccountEntity.getAccountNumber(),vendorAccountEntity.getBankName(),vendorAccountEntity.getBranchName()));
        }
        return vendorAccountsGet;
    }
    

    protected abstract String getSelfLink(String id);
}
