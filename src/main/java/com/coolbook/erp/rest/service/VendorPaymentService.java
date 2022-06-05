package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.VendorEntity;
import com.coolbook.erp.entity.VendorPaymentEntity;
import com.coolbook.erp.repository.VendorAccountRepository;
import com.coolbook.erp.repository.VendorPaymentRepository;
import com.coolbook.erp.repository.VendorRepository;
import com.coolbook.erp.repository.specs.VendorPaymentSearchSpecification;
import com.coolbook.erp.repository.specs.VendorPaymentSpecification;
import com.coolbook.erp.repository.specs.VendorSearchSpecification;
import com.coolbook.erp.repository.specs.VendorSpecification;
import com.coolbook.erp.rest.searchCriteria.VendorCriteria;
import com.coolbook.erp.rest.searchCriteria.VendorPaymentCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VendorPaymentService {
    
    @Autowired
    private VendorPaymentRepository vendorPaymentRepository;
    
    

    @Autowired
    private VendorAccountRepository vendorAccountRepository;


    public long saveVendorPayment(VendorPaymentEntity vendorPayment){
        long id=this.vendorPaymentRepository.save(vendorPayment).getId();
        return id;
    }

    public void updateVendor(VendorPaymentEntity vendorPayment, long id) {
        vendorPayment.setId(id);
        this.vendorPaymentRepository.save(vendorPayment);
    }

    public void deleteVendorPayment(long id) {

        this.vendorPaymentRepository.delete(id);
    }

    public VendorPaymentEntity getVendorPaymentById(long id) {
        return this.vendorPaymentRepository.getOne(id);
    }

    public Page<VendorPaymentEntity> getAllVendorPayment(Pageable page, VendorPaymentCriteria criteria) {
        VendorPaymentSpecification specification = new VendorPaymentSpecification(criteria);
        return this.vendorPaymentRepository.findAll(specification, page);
    }

    public Page<VendorPaymentEntity> searchVendor(Pageable page, String searchValue) {
        VendorPaymentSearchSpecification specification = new VendorPaymentSearchSpecification(searchValue);
        return this.vendorPaymentRepository.findAll(specification, page);
    }
    
}