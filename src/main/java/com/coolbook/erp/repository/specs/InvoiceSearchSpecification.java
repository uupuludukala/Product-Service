package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.InvoiceEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.text.ParseException;
import java.util.Date;

public class InvoiceSearchSpecification extends SearchSpecification implements Specification<InvoiceEntity> {
    
    private String searchValue;

    public InvoiceSearchSpecification(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public Predicate toPredicate(Root<InvoiceEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.or();
        if (searchValue != null) {
            Expression<String> invoiceNumber = root.get("invoiceNumber");
            predicate = cb.like(cb.upper(invoiceNumber), "%" + searchValue.toUpperCase() + "%");

            Expression<String> customerName = root.get("customer").get("customerName");
            predicate = cb.or(predicate, cb.like(cb.upper(customerName), "%" + searchValue.toUpperCase() + "%"));

            Expression<String> nicNumber = root.get("customer").get("nicNumber");
            predicate = cb.or(predicate, cb.like(cb.upper(nicNumber), "%" + searchValue.toUpperCase() + "%"));

            Expression<String> customerMobileNumber = root.get("customer").get("mobileNumber");
            predicate = cb.or(predicate, cb.like(cb.upper(customerMobileNumber), "%" + searchValue.toUpperCase() + "%"));

            
            if(!"".equals(searchValue) && isDateValid(searchValue)) {
                Path<Tuple> tuple = root.<Tuple>get("date");
                if(tuple.getJavaType().isAssignableFrom(Date.class)){
                    Expression<String> dateStringExpr = cb.function("TO_CHAR", String.class, root.get("date"), cb.literal("'YYYY-MM-DD'"));
                    try {
                        predicate=cb.or(predicate,cb.like(cb.lower(dateStringExpr), "%"+dateFormat.format(dateFormat2.parse(searchValue)).toLowerCase()+"%"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return predicate;
    }

   
}
