package com.coolbook.erp.repository.specs;


import com.coolbook.erp.entity.VendorPaymentEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.text.ParseException;
import java.util.Date;

public class VendorPaymentSearchSpecification extends SearchSpecification  implements Specification<VendorPaymentEntity> {

    private String searchvalue;

    public VendorPaymentSearchSpecification(String searchvalue) {
        this.searchvalue = searchvalue;
    }

    @Override
    public Predicate toPredicate(Root<VendorPaymentEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchvalue != null) {
            Expression<String> vendorName = root.get("vendor").get("vendorName");
            predicate =  cb.like(cb.upper(vendorName), "%" + searchvalue.toUpperCase() + "%");

            Expression<String> bankName = root.get("bankName");
            predicate = cb.or(predicate,cb.like(cb.upper(bankName), "%" + searchvalue.toUpperCase() + "%"));

            Expression<String> bankBranch = root.get("bankBranch");
            predicate = cb.or(predicate,cb.like(cb.upper(bankBranch), "%" + searchvalue.toUpperCase() + "%"));

            Expression<String> accountNumber = root.get("accountNumber");
            predicate = cb.or(predicate,cb.like(cb.upper(accountNumber), "%" + searchvalue.toUpperCase() + "%"));

            if(!"".equals(searchvalue) && isDateValid(searchvalue)) {
                Path<Tuple> tuple = root.<Tuple>get("date");
                if(tuple.getJavaType().isAssignableFrom(Date.class)){
                    Expression<String> dateStringExpr = cb.function("TO_CHAR", String.class, root.get("date"), cb.literal("'YYYY-MM-DD'"));
                    try {
                        predicate=cb.or(predicate,cb.like(cb.lower(dateStringExpr), "%"+dateFormat.format(dateFormat2.parse(searchvalue)).toLowerCase()+"%"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }                
        }
        return predicate;
    }
}
