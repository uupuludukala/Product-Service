package com.coolbook.erp.repository.specs;


import com.coolbook.erp.entity.VendorPaymentEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class VendorPaymentSearchSpecification  implements Specification<VendorPaymentEntity> {

    private String searchvalue;

    public VendorPaymentSearchSpecification(String searchvalue) {
        this.searchvalue = searchvalue;
    }

    @Override
    public Predicate toPredicate(Root<VendorPaymentEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchvalue != null) {
//            Expression<String> vendorName = root.get("vendor").get("vendorName");
//            predicate = cb.like(cb.upper(vendorName), "%" + searchvalue.toUpperCase() + "%");
//            Expression<String> date = root.get("date");
//            predicate = cb.or(predicate, cb.like(cb.upper(date), "%" + searchvalue.toUpperCase() + "%"));           
        }
        return predicate;
    }
}
