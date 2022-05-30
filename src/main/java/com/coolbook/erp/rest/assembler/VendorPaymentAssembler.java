package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.VendorPaymentEntity;
import com.coolbook.erp.model.VendorPaymentGet;
import com.coolbook.erp.model.VendorPaymentPost;
import com.coolbook.erp.rest.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class VendorPaymentAssembler {
    
    @Autowired
    private VendorService vendorService;

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public VendorPaymentEntity assembleVendorPaymentEntity(VendorPaymentPost vendorPaymentPost) {
        VendorPaymentEntity vendorPaymentEntity=new VendorPaymentEntity();
        vendorPaymentEntity.setAccountNumber(vendorPaymentPost.getAccountNumber());
        vendorPaymentEntity.setAmount(vendorPaymentPost.getAmount());
        vendorPaymentEntity.setBankBranch(vendorPaymentPost.getBankBranch());
        vendorPaymentEntity.setBankName(vendorPaymentPost.getBankName());
        vendorPaymentEntity.setDate(vendorPaymentPost.getDate());
        vendorPaymentEntity.setRemarks(vendorPaymentPost.getRemarks());
        vendorPaymentEntity.setStatus(vendorPaymentPost.getStatus());
        vendorPaymentEntity.setVendor(vendorService.getVendorById(vendorPaymentPost.getVendorId()));
        return vendorPaymentEntity;
    }
    
    public VendorPaymentGet assembleVendorPaymentGet(VendorPaymentEntity vendorPaymentEntity){
        VendorPaymentGet vendorPaymentGet= new VendorPaymentGet();
        vendorPaymentGet.setVendor_payment_id(vendorPaymentEntity.getId());
        vendorPaymentGet.setRemarks(vendorPaymentEntity.getRemarks());
        vendorPaymentGet.setAmount(vendorPaymentEntity.getAmount());
        vendorPaymentGet.setAccountNumber(vendorPaymentEntity.getAccountNumber());
        vendorPaymentGet.setBankBranch(vendorPaymentEntity.getBankBranch());
        vendorPaymentGet.setBankName(vendorPaymentEntity.getBankName());
        vendorPaymentGet.setDate(dateFormat.format(vendorPaymentEntity.getDate()));
        vendorPaymentGet.setVendorId(vendorPaymentEntity.getVendor().getId());
        vendorPaymentGet.setVendorName(vendorPaymentEntity.getVendor().getVendorName());
        return vendorPaymentGet;
    }
}
