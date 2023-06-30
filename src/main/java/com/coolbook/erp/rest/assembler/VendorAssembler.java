package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.VendorAccountEntity;
import com.coolbook.erp.entity.VendorEntity;
import com.coolbook.erp.model.VendorAccount;
import com.coolbook.erp.model.VendorAccountGet;
import com.coolbook.erp.model.VendorGet;
import com.coolbook.erp.model.VendorPost;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class VendorAssembler {
    public VendorGet assembleVendorGet(VendorEntity vendorEntity) {
        VendorGet vendorGet = new VendorGet();
        vendorGet.setVendor_Id(vendorEntity.getId());
        vendorGet.setVendorType(vendorEntity.getVendorType());
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

    public Set<VendorAccountGet> assembleVendorAccountGet(Set<VendorAccountEntity> vendorAccounts){
        Set<VendorAccountGet> vendorAccountsGet= new HashSet<>();
        for(VendorAccountEntity vendorAccountEntity:vendorAccounts){
            vendorAccountsGet.add(new VendorAccountGet(vendorAccountEntity.getId(),
                    vendorAccountEntity.getAccountNumber(),vendorAccountEntity.getBankName(),vendorAccountEntity.getBranchName()));
        }
        return vendorAccountsGet;
    }

    public VendorEntity essembleVendorEntity(VendorPost vendorPost) {
        VendorEntity vendorEntity=new VendorEntity();
        vendorEntity.setVendorType(vendorPost.getVendorType());
        vendorEntity.setVendorName(vendorPost.getVendorName());
        vendorEntity.setAddressLine1(vendorPost.getAddressLine1());
        vendorEntity.setAddressLine2(vendorPost.getAddressLine2());
        vendorEntity.setAddressLine3(vendorPost.getAddressLine3());
        vendorEntity.setContactPerson(vendorPost.getContactPerson());
        vendorEntity.setPhone(vendorPost.getPhone());
        vendorEntity.setMobile(vendorPost.getMobile());
        vendorEntity.setEmail(vendorPost.getEmail());
        vendorEntity.setVendorAccounts(assembleVendorAccountEntity(vendorPost.getVendorAccounts(),vendorEntity));
        return vendorEntity;
    }

    public Set<VendorAccountEntity> assembleVendorAccountEntity(Set<VendorAccount> vendorAccounts,VendorEntity vendorEntity) {
        Set<VendorAccountEntity> vendorAccountEntities=new HashSet<>();
        if(vendorAccounts!=null) {
            for (VendorAccount vendorAccount : vendorAccounts) {
                VendorAccountEntity vendorAccountEntity = new VendorAccountEntity();
                vendorAccountEntity.setAccountNumber(vendorAccount.getAccountNumber());
                vendorAccountEntity.setBankName(vendorAccount.getBankName());
                vendorAccountEntity.setBranchName(vendorAccount.getBranchName());
                vendorAccountEntity.setVendor(vendorEntity);
                vendorAccountEntities.add(vendorAccountEntity);
            }
        }
        return vendorAccountEntities;
    }
}
