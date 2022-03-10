package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.VendorEntity;
import com.coolbook.erp.rest.searchCriteria.VendorCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class VendorSpecification implements Specification<VendorEntity> {

    private VendorCriteria searchCriteria;

    public VendorSpecification(VendorCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<VendorEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        
        
        if (searchCriteria.getVendorName() != null) {
            Expression<String> vendorName = root.get("vendorName");
            predicate = cb.and(predicate, cb.like(cb.upper(vendorName), "%" + searchCriteria.getVendorName().toUpperCase() + "%"));
        }
        if (searchCriteria.getContactPerson() != null) {
            Expression<String> contactPerson = root.get("contactPerson");
            predicate = cb.and(predicate, cb.like(cb.upper(contactPerson), "%" + searchCriteria.getContactPerson().toUpperCase() + "%"));
        }
        if (searchCriteria.getPhone() != null) {
            Expression<String> phone = root.get("phone");
            predicate = cb.and(predicate, cb.like(phone, "%" + searchCriteria.getPhone() + "%"));
        }
        if (searchCriteria.getMobile()!= null) {
            Expression<String> mobile = root.get("mobile");
            predicate = cb.and(predicate, cb.like(mobile, "%" + searchCriteria.getMobile() + "%"));
        }

        return predicate;
    }



}
