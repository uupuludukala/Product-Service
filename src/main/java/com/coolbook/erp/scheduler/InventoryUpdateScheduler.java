package com.coolbook.erp.scheduler;

import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.entity.ProductInventoryDetailsEntity;
import com.coolbook.erp.entity.PurchaseOrderEntity;
import com.coolbook.erp.entity.PurchaseOrderProductEntity;
import com.coolbook.erp.rest.service.ProductInventoryDetailsService;
import com.coolbook.erp.rest.service.ProductService;
import com.coolbook.erp.rest.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;


//
//@Configuration
//@EnableScheduling
public class InventoryUpdateScheduler {

    @Autowired
    private PurchaseOrderService purchaseOrderService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductInventoryDetailsService productInventoryDetailsService;
    
//    @Scheduled(fixedRate = 5000)
    public void scheduleFixedDelayTask() {
        List<PurchaseOrderEntity> purchaseOrders=purchaseOrderService.getNotApprovedPurchaseOrders();
        
        for(PurchaseOrderEntity purchaseOrderEntity:purchaseOrders){
            productInventoryDetailsService.deleteAllProductForPurchaseOrder(productInventoryDetailsService.getProductByPurchaseOrder(purchaseOrderEntity));
            for(PurchaseOrderProductEntity purchaseOrderProduct:purchaseOrderEntity.getPurchaseOrderProducts()){
                ProductEntity product=purchaseOrderProduct.getProduct();
                product.setQuantity(product.getQuantity() + purchaseOrderProduct.getQuantity());
                product.setSalePrice(purchaseOrderProduct.getRate());
                if(purchaseOrderProduct.getAmount()!=0)
                    product.setCost(purchaseOrderProduct.getAmount()/purchaseOrderProduct.getQuantity());
                productService.updateProduct(product);
                saveProductInventoryDetails(purchaseOrderProduct,purchaseOrderEntity);
            }
            purchaseOrderEntity.setApproved(true);
            purchaseOrderService.updatePurchaseOrder(purchaseOrderEntity);
        }
    }
    
    private void saveProductInventoryDetails(PurchaseOrderProductEntity purchaseOrderProduct, PurchaseOrderEntity purchaseOrder){
        ProductInventoryDetailsEntity productInventoryDetails = new ProductInventoryDetailsEntity();
        productInventoryDetails.setDate(purchaseOrder.getDate());
        productInventoryDetails.setPurchaseOrder(purchaseOrder);
        productInventoryDetails.setProduct(purchaseOrderProduct.getProduct());
        productInventoryDetails.setRate(purchaseOrderProduct.getAmount()/purchaseOrderProduct.getQuantity());
        productInventoryDetails.setQuantity(purchaseOrderProduct.getQuantity());
        productInventoryDetailsService.saveProductInventoryDetails(productInventoryDetails);
    }
}
