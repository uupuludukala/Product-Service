package com.coolbook.erp.repository;


import com.coolbook.erp.entity.PurchaseOrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PurchaseOrderProductRepository extends JpaRepository<PurchaseOrderProductEntity, Long>, JpaSpecificationExecutor<PurchaseOrderProductEntity> {
}
