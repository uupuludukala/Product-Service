package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.entity.PurchaseOrderEntity;
import com.coolbook.erp.entity.PurchaseOrderProductEntity;
import com.coolbook.erp.model.*;
import com.coolbook.erp.rest.service.ProductService;
import com.coolbook.erp.rest.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PurchaseOrderAssembler {
    
    @Autowired
    private ProductService productService;

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    
    @Autowired
    private VendorService vendorService;
    
    public PurchaseOrderGet assemblePurchaseOrderGet(PurchaseOrderEntity purchaseOrderEntity) {
        PurchaseOrderGet purchaseOrderGet = new PurchaseOrderGet();
        purchaseOrderGet.setPurchase_order_id(purchaseOrderEntity.getId());
        purchaseOrderGet.setPurchaseOrderNumber(purchaseOrderEntity.getPurchaseOrderNumber());
        purchaseOrderGet.setDate(dateFormat2.format(purchaseOrderEntity.getDate()));
        purchaseOrderGet.setPurchaseOrderProducts(assemblePurchaseOrderProductsGet(purchaseOrderEntity.getPurchaseOrderProducts()));
        purchaseOrderGet.setTotal(purchaseOrderEntity.getTotal());
        purchaseOrderGet.setVendorName(purchaseOrderEntity.getVendor().getVendorName());
        purchaseOrderGet.setVendorId(purchaseOrderEntity.getVendor().getId());
        return purchaseOrderGet;
    }

    private List<PurchaseOrderProductGet> assemblePurchaseOrderProductsGet(List<PurchaseOrderProductEntity> purchaseOrderAccounts){
        List<PurchaseOrderProductGet> purchaseOrderAccountsGet= new ArrayList<>();
        for(PurchaseOrderProductEntity purchaseOrderProductEntity:purchaseOrderAccounts){
            ProductEntity product=purchaseOrderProductEntity.getProduct();
            purchaseOrderAccountsGet.add(new PurchaseOrderProductGet(purchaseOrderProductEntity.getId(),product.getId(),
                    product.getProductCode(),product.getProductName(),purchaseOrderProductEntity.getRate(),purchaseOrderProductEntity.getDiscount(),
                    purchaseOrderProductEntity.getQuantity(),purchaseOrderProductEntity.getAmount()));
        }
        return purchaseOrderAccountsGet;
    }

    public PurchaseOrderEntity assemblePurchaseOrderEntity(PurchaseOrderPost purchaseOrderPost) {
        PurchaseOrderEntity purchaseOrderEntity=new PurchaseOrderEntity();
        purchaseOrderEntity.setPurchaseOrderNumber(purchaseOrderPost.getPurchaseOrderNumber());
        purchaseOrderEntity.setDate(purchaseOrderPost.getDate());
        purchaseOrderEntity.setTotal(purchaseOrderPost.getTotal());
        purchaseOrderEntity.setPurchaseOrderProducts(assemblePurchaseOrderProductEntity(purchaseOrderPost,purchaseOrderEntity));
        purchaseOrderEntity.setVendor(vendorService.getVendorById(purchaseOrderPost.getVendorId()));
        return purchaseOrderEntity;
    }

    public List<PurchaseOrderProductEntity> assemblePurchaseOrderProductEntity(PurchaseOrderPost purchaseOrderPost, PurchaseOrderEntity purchaseOrderEntity) {
        List<PurchaseOrderProductEntity> purchaseOrderAccountEntities=new ArrayList<>();
        List<PurchaseOrderProduct> purchaseOrderProducts=purchaseOrderPost.getPurchaseOrderProducts();
        if(purchaseOrderProducts!=null) {
            for (PurchaseOrderProduct purchaseOrderProduct : purchaseOrderProducts) {
                PurchaseOrderProductEntity purchaseOrderProductEntity = new PurchaseOrderProductEntity();
                purchaseOrderProductEntity.setRate(purchaseOrderProduct.getRate());
                purchaseOrderProductEntity.setDiscount(purchaseOrderProduct.getDiscount());
                purchaseOrderProductEntity.setQuantity(purchaseOrderProduct.getQuantity());
                purchaseOrderProductEntity.setAmount(purchaseOrderProduct.getAmount());
                purchaseOrderProductEntity.setProduct(productService.getProductById(purchaseOrderProduct.getProductId()));
                purchaseOrderProductEntity.setPurchaseOrder(purchaseOrderEntity);
                purchaseOrderAccountEntities.add(purchaseOrderProductEntity);
            }
        }
        return purchaseOrderAccountEntities;
    }
}
