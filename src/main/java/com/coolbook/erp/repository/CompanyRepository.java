package com.coolbook.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.coolbook.erp.entity.CompanyEntity;

@Transactional
public interface CompanyRepository  extends JpaRepository<CompanyEntity, Long>,JpaSpecificationExecutor<CompanyEntity>{

}
