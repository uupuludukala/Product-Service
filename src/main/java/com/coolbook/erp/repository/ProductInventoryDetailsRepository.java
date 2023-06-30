package com.coolbook.erp.repository;


import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.entity.ProductInventoryDetailsEntity;
import com.coolbook.erp.entity.PurchaseOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInventoryDetailsRepository extends JpaRepository<ProductInventoryDetailsEntity, Long>, JpaSpecificationExecutor<ProductInventoryDetailsEntity> {
    @Query(value="from ProductInventoryDetailsEntity p where p.product =?1 and quantity!=0 order by date ")
    List<ProductInventoryDetailsEntity> getAvailableProducts(ProductEntity productEntity);

    @Query(value="from ProductInventoryDetailsEntity p where p.purchaseOrder =?1 ")
    List<ProductInventoryDetailsEntity> getProductByPurchaseOrder(PurchaseOrderEntity purchaseOrder);

   
    
}
