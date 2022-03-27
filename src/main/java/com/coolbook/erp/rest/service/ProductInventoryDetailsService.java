package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.entity.ProductInventoryDetailsEntity;
import com.coolbook.erp.entity.PurchaseOrderEntity;
import com.coolbook.erp.repository.ProductInventoryDetailsRepository;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductInventoryDetailsService {
    
    @Autowired
    private ProductInventoryDetailsRepository productInventoryDetailsRepository;
    
    public void saveProductInventoryDetails(ProductInventoryDetailsEntity productInventoryDetailsEntity){
       this.productInventoryDetailsRepository.save(productInventoryDetailsEntity);
    }
    
    public List<ProductInventoryDetailsEntity> getAvailableProducts(ProductEntity productEntity){
        return productInventoryDetailsRepository.getAvailableProducts(productEntity);
    }

    public List<ProductInventoryDetailsEntity> getProductByPurchaseOrder(PurchaseOrderEntity purchaseOrder){
        return productInventoryDetailsRepository.getProductByPurchaseOrder(purchaseOrder);
    }

    public void deleteAllProductForPurchaseOrder(List<ProductInventoryDetailsEntity> purchaseOrders){
         productInventoryDetailsRepository.deleteInBatch((Iterable<ProductInventoryDetailsEntity>)purchaseOrders);
    }
    
    
}
