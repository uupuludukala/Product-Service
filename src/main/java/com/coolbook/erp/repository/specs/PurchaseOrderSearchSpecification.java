package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.PurchaseOrderEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseOrderSearchSpecification extends SearchSpecification implements Specification<PurchaseOrderEntity>  {

    private String searchValue;

    public PurchaseOrderSearchSpecification(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public Predicate toPredicate(Root<PurchaseOrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (searchValue != null) {
            Expression<String> purchaseOrderName = root.get("purchaseOrderNumber");
            predicate = cb.like(cb.upper(purchaseOrderName), "%" + searchValue.toUpperCase() + "%");

            Expression<String> vendorName = root.get("vendor").get("vendorName");
            predicate =  cb.or(predicate,cb.like(cb.upper(vendorName), "%" + searchValue.toUpperCase() + "%"));
            
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
