package com.coolbook.erp.rest.assembler;


import com.coolbook.erp.entity.VendorPaymentEntity;
import com.coolbook.erp.model.VendorPaymentGet;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public abstract class AbstractVendorPaymentResourceAssembler extends ResourceAssemblerSupport<VendorPaymentEntity, VendorPaymentGet> {
    protected String requestURI;

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public AbstractVendorPaymentResourceAssembler() {
        super(VendorPaymentEntity.class, VendorPaymentGet.class);
    }

    @Override
    public VendorPaymentGet toResource(VendorPaymentEntity vendorPaymentEntity) {
        return createVendorJson(vendorPaymentEntity);
    }

    private VendorPaymentGet createVendorJson(VendorPaymentEntity vendorPaymentEntity) {
        VendorPaymentGet vendorPaymentGet = new VendorPaymentGet();
        vendorPaymentGet.setVendor_payment_id(vendorPaymentEntity.getId());
        vendorPaymentGet.setVendorId(vendorPaymentEntity.getVendor().getId());
        vendorPaymentGet.setVendorName(vendorPaymentEntity.getVendor().getVendorName());
        vendorPaymentGet.setDate(dateFormat.format(vendorPaymentEntity.getDate()));
        vendorPaymentGet.setBankName(vendorPaymentEntity.getBankName());
        vendorPaymentGet.setBankBranch(vendorPaymentEntity.getBankBranch());
        vendorPaymentGet.setAccountNumber(vendorPaymentEntity.getAccountNumber());
        vendorPaymentGet.setAmount(vendorPaymentEntity.getAmount());
        vendorPaymentGet.setRemarks(vendorPaymentEntity.getRemarks());   
        return vendorPaymentGet;
    }

    


    protected abstract String getSelfLink(String id);
}