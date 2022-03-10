package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.entity.PurchaseOrderEntity;
import com.coolbook.erp.entity.PurchaseOrderProductEntity;
import com.coolbook.erp.model.PurchaseOrderGet;
import com.coolbook.erp.model.PurchaseOrderProductGet;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;


public abstract class AbstractPurchaseOrderResourceAssembler extends ResourceAssemblerSupport<PurchaseOrderEntity, PurchaseOrderGet> {
    protected String requestURI;

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
   

    public AbstractPurchaseOrderResourceAssembler() {
        super(PurchaseOrderEntity.class, PurchaseOrderGet.class);
    }

    @Override
    public PurchaseOrderGet toResource(PurchaseOrderEntity purchaseOrderEntity) {
        return createPurchaseOrderJson(purchaseOrderEntity);
    }

    private PurchaseOrderGet createPurchaseOrderJson(PurchaseOrderEntity purchaseOrderEntity) {
        PurchaseOrderGet purchaseOrderGet = new PurchaseOrderGet();
        purchaseOrderGet.setPurchase_order_id(purchaseOrderEntity.getId());
        purchaseOrderGet.setPurchaseOrderNumber(purchaseOrderEntity.getPurchaseOrderNumber());
        purchaseOrderGet.setDate(dateFormat.format(purchaseOrderEntity.getDate()));
        purchaseOrderGet.setPurchaseOrderProducts(assemblePurchaseOrderProductsGet(purchaseOrderEntity.getPurchaseOrderProducts()));
        purchaseOrderGet.setTotal(purchaseOrderEntity.getTotal());
        purchaseOrderGet.setVendorName(purchaseOrderEntity.getVendor().getVendorName());
        purchaseOrderGet.setVendorId(purchaseOrderEntity.getVendor().getId());
        return purchaseOrderGet;
    }

    private Set<PurchaseOrderProductGet> assemblePurchaseOrderProductsGet(Set<PurchaseOrderProductEntity> purchaseOrderAccounts){
        Set<PurchaseOrderProductGet> purchaseOrderAccountsGet= new HashSet<>();
        for(PurchaseOrderProductEntity purchaseOrderProductEntity:purchaseOrderAccounts){
            ProductEntity product=purchaseOrderProductEntity.getProduct();
            purchaseOrderAccountsGet.add(new PurchaseOrderProductGet(purchaseOrderProductEntity.getId(),product.getId(),
                    product.getProductCode(),product.getProductName(),purchaseOrderProductEntity.getRate(),purchaseOrderProductEntity.getDiscount(),
                    purchaseOrderProductEntity.getQuantity(),purchaseOrderProductEntity.getAmount()));
        }
        return purchaseOrderAccountsGet;
    }
    
    protected abstract String getSelfLink(String id);
}
