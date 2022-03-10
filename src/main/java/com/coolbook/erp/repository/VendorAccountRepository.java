package com.coolbook.erp.repository;

import com.coolbook.erp.entity.VendorAccountEntity;
import com.coolbook.erp.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VendorAccountRepository extends JpaRepository<VendorAccountEntity, Long>, JpaSpecificationExecutor<VendorEntity> {
}
