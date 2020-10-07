package com.coolbook.erp.repository.specs;

import com.coolbook.erp.entity.BranchEntity;
import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class UserSearchSpecification implements Specification<UserEntity> {

    private String searchValue;
    private long branchId;

    public UserSearchSpecification(String searchValue, long branchId) {
        this.searchValue = searchValue;
        this.branchId = branchId;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {


        Predicate predicate =null;
        if (searchValue != null) {
            Expression<String> userName = root.get("userName");
            predicate =  cb.like(cb.upper(userName), "%" + searchValue.toUpperCase() + "%");           
        }
        return predicate;
    }

    
}
