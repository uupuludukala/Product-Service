package com.coolbook.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coolbook.erp.entity.CompanyEntity;

@Repository
public interface CompanyRepository  extends JpaRepository<CompanyEntity, Long>{

}
