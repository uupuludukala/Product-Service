package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.VendorAccountEntity;
import com.coolbook.erp.entity.VendorEntity;
import com.coolbook.erp.repository.VendorAccountRepository;
import com.coolbook.erp.repository.VendorRepository;
import com.coolbook.erp.repository.specs.VendorSearchSpecification;
import com.coolbook.erp.repository.specs.VendorSpecification;
import com.coolbook.erp.rest.searchCriteria.VendorCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;
    
    @Autowired
    private VendorAccountRepository vendorAccountRepository;
   

    public long saveVendor(VendorEntity vendor){
        long id=this.vendorRepository.save(vendor).getId();
        return id;
    }

    public void updateVendor(VendorEntity vendor, long id) {
        vendor.setId(id);
        this.vendorRepository.save(vendor);
    }

    public void deleteVendor(long id) {

        this.vendorRepository.delete(id);
    }

    public VendorEntity getVendorById(long id) {
        return this.vendorRepository.getOne(id);
    }

    public Page<VendorEntity> getAllVendor(Pageable page, VendorCriteria criteria) {
        VendorSpecification specification = new VendorSpecification(criteria);
        return this.vendorRepository.findAll(specification, page);
    }

    public Page<VendorEntity> searchVendor(Pageable page, String searchValue) {
        VendorSearchSpecification specification = new VendorSearchSpecification(searchValue);
        return this.vendorRepository.findAll(specification, page);
    }
}
