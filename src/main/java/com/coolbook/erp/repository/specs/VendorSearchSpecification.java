package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.VendorEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class VendorSearchSpecification implements Specification<VendorEntity> {

    private String searchvalue;

    public VendorSearchSpecification(String searchvalue) {
        this.searchvalue = searchvalue;
    }

    @Override
    public Predicate toPredicate(Root<VendorEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchvalue != null) {
            Expression<String> vendorName = root.get("vendorName");
            predicate = cb.like(cb.upper(vendorName), "%" + searchvalue.toUpperCase() + "%");
            Expression<String> contactPerson = root.get("contactPerson");
            predicate = cb.or(predicate, cb.like(cb.upper(contactPerson), "%" + searchvalue.toUpperCase() + "%"));
            Expression<String> phone = root.get("phone");
            predicate = cb.or(predicate, cb.like(phone, "%" + searchvalue + "%"));
            Expression<String> mobile = root.get("mobile");
            predicate = cb.or(predicate, cb.like(mobile, "%" + searchvalue + "%"));
        }
        return predicate;
    }

}
