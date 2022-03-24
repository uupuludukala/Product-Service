package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.entity.ProductInventoryDetailsEntity;
import com.coolbook.erp.model.ProductInventoryDetailsGet;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductInventoryDetailsAssembler {

    DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    
    public List<ProductInventoryDetailsGet> productInventoryDetailsGetList(List<ProductInventoryDetailsEntity> productInventoryDetailsEntityList){
        List<ProductInventoryDetailsGet> productInventoryDetailsGets=new ArrayList<>();
        for(ProductInventoryDetailsEntity productInventoryDetailsEntity:productInventoryDetailsEntityList){
            ProductInventoryDetailsGet productInventoryDetailsGet=new ProductInventoryDetailsGet();
            ProductEntity productEntity=productInventoryDetailsEntity.getProduct();
            productInventoryDetailsGet.setProductName(productEntity.getProductName());
            productInventoryDetailsGet.setProductCode(productEntity.getProductCode());
            productInventoryDetailsGet.setPurchaseOrderNumber(productInventoryDetailsEntity.getPurchaseOrder().getPurchaseOrderNumber());
            productInventoryDetailsGet.setDate(dateFormat2.format(productInventoryDetailsEntity.getDate()));
            productInventoryDetailsGet.setRate(productInventoryDetailsEntity.getRate());
            productInventoryDetailsGet.setCost(productInventoryDetailsEntity.getCost());
            productInventoryDetailsGet.setQuantity(productInventoryDetailsEntity.getQuantity());            
            productInventoryDetailsGets.add(productInventoryDetailsGet);
        }
        return productInventoryDetailsGets;
    }
}
