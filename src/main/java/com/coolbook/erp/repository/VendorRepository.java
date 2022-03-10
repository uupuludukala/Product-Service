package com.coolbook.erp.repository;


import com.coolbook.erp.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VendorRepository extends JpaRepository<VendorEntity, Long>, JpaSpecificationExecutor<VendorEntity> {
}
