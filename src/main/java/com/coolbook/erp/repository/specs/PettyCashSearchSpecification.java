package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.PettyCashEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.text.ParseException;
import java.util.Date;

public class PettyCashSearchSpecification extends SearchSpecification implements Specification<PettyCashEntity> {

    private String searchValue;
    private long companyId;

    public PettyCashSearchSpecification(String searchValue, long companyId) {
        this.searchValue = searchValue;
        this.companyId = companyId;
    }

    @Override
    public Predicate toPredicate(Root<PettyCashEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {


        Predicate predicate = cb.conjunction();
        if (searchValue != null) {
            Expression<String> description = root.get("description");
            predicate = cb.or(predicate,cb.like(cb.upper(description), "%" + searchValue.toUpperCase() + "%"));

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
