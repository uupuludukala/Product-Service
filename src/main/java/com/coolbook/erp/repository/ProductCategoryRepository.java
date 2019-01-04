package com.coolbook.erp.repository;

import org.springframework.stereotype.Repository;

import com.coolbook.erp.entity.ProductCategoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity,Long> {

}
