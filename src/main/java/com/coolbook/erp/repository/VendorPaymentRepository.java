package com.coolbook.erp.repository;

import com.coolbook.erp.entity.VendorPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VendorPaymentRepository extends JpaRepository<VendorPaymentEntity, Long>, JpaSpecificationExecutor<VendorPaymentEntity> {
}
