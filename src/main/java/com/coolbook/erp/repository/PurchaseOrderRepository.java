package com.coolbook.erp.repository;

import com.coolbook.erp.entity.PurchaseOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderEntity, Long>, JpaSpecificationExecutor<PurchaseOrderEntity> {
    @Query(value="from PurchaseOrderEntity po where po.approved =false")
    List<PurchaseOrderEntity> getNotApprovedPurchaseOrders();
}
