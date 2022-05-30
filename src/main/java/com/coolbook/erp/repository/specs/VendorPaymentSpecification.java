package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.VendorPaymentEntity;
import com.coolbook.erp.rest.searchCriteria.VendorPaymentCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class VendorPaymentSpecification implements Specification<VendorPaymentEntity> {

    private VendorPaymentCriteria searchCriteria;

    public VendorPaymentSpecification(VendorPaymentCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<VendorPaymentEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();


        if (searchCriteria.getVendorName() != null) {
            Expression<String> vendorName = root.get("vendorName");
            predicate = cb.and(predicate, cb.like(cb.upper(vendorName), "%" + searchCriteria.getVendorName().toUpperCase() + "%"));
        }
        if (searchCriteria.getDate() != null) {
            Expression<String> date = root.get("date");
            predicate = cb.and(predicate, cb.like(cb.upper(date), "%" + searchCriteria.getDate() + "%"));
        }
      

        return predicate;
    }



}
