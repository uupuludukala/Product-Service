package com.coolbook.erp.repository;

import com.coolbook.erp.entity.PurchaseOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderEntity, Long>, JpaSpecificationExecutor<PurchaseOrderEntity> {
}
