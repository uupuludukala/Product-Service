package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.entity.ProductInventoryDetailsEntity;
import com.coolbook.erp.entity.PurchaseOrderEntity;
import com.coolbook.erp.entity.PurchaseOrderProductEntity;
import com.coolbook.erp.repository.PurchaseOrderRepository;
import com.coolbook.erp.repository.specs.PurchaseOrderSearchSpecification;
import com.coolbook.erp.repository.specs.PurchaseOrderSpecification;
import com.coolbook.erp.rest.searchCriteria.PurchaseOrderCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;


@Service
public class PurchaseOrderService extends BaseService{

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductInventoryDetailsService productInventoryDetailsService;
    
    

    public long savePurchaseOrder(PurchaseOrderEntity purchaseOrder){
        setMetaData(purchaseOrder,null);
        long id=this.purchaseOrderRepository.save(purchaseOrder).getId();
        return id;
    }

    public void updatePurchaseOrder(PurchaseOrderEntity purchaseOrder) {
        setMetaData(purchaseOrder,this.purchaseOrderRepository.getOne(purchaseOrder.getId()));
        this.purchaseOrderRepository.save(purchaseOrder);
    }

    public void deletePurchaseOrder(long id) {
        PurchaseOrderEntity purchaseOrderEntity=getPurchaseOrderById(id);
        if(!purchaseOrderEntity.isApproved()) {
            this.purchaseOrderRepository.delete(id);
        }else{
            throw new HttpServerErrorException(HttpStatus.CONFLICT);
        }
    }

    public PurchaseOrderEntity getPurchaseOrderById(long id) {
        return this.purchaseOrderRepository.getOne(id);
    }

    public Page<PurchaseOrderEntity> getAllPurchaseOrder(Pageable page, PurchaseOrderCriteria criteria) {
        PurchaseOrderSpecification specification = new PurchaseOrderSpecification();
        return this.purchaseOrderRepository.findAll(specification, page);
    }

    public Page<PurchaseOrderEntity> searchPurchaseOrder(Pageable page, String searchValue) {
        PurchaseOrderSearchSpecification specification = new PurchaseOrderSearchSpecification(searchValue);
        return this.purchaseOrderRepository.findAll(specification, page);
    }
    
    public List<PurchaseOrderEntity> getNotApprovedPurchaseOrders(){
        return this.purchaseOrderRepository.getNotApprovedPurchaseOrders();
    }
    
    public void approvePurchaseOrder(long purchaseOrderId){
        PurchaseOrderEntity purchaseOrder = purchaseOrderService.getPurchaseOrderById(purchaseOrderId); 
        if(!purchaseOrder.isApproved()) {
            for (PurchaseOrderProductEntity purchaseOrderProduct : purchaseOrder.getPurchaseOrderProducts()) {
                ProductEntity product = purchaseOrderProduct.getProduct();
                product.setQuantity(product.getQuantity() + purchaseOrderProduct.getQuantity());
                product.setSalePrice(purchaseOrderProduct.getRate());
                if (purchaseOrderProduct.getAmount() != 0)
                    product.setCost(purchaseOrderProduct.getAmount() / purchaseOrderProduct.getQuantity());
                productService.updateProduct(product);
                saveProductInventoryDetails(purchaseOrderProduct, purchaseOrder);
            }
            purchaseOrder.setApproved(true);
            purchaseOrderService.updatePurchaseOrder(purchaseOrder);
        }
    }

    private void saveProductInventoryDetails(PurchaseOrderProductEntity purchaseOrderProduct, PurchaseOrderEntity purchaseOrder){
        ProductInventoryDetailsEntity productInventoryDetails = new ProductInventoryDetailsEntity();
        productInventoryDetails.setDate(purchaseOrder.getDate());
        productInventoryDetails.setPurchaseOrder(purchaseOrder);
        productInventoryDetails.setProduct(purchaseOrderProduct.getProduct());
        productInventoryDetails.setRate(purchaseOrderProduct.getRate());
        productInventoryDetails.setCost(purchaseOrderProduct.getAmount()/purchaseOrderProduct.getQuantity());
        productInventoryDetails.setQuantity(purchaseOrderProduct.getQuantity());
        productInventoryDetailsService.saveProductInventoryDetails(productInventoryDetails);
    }

    public List<ProductInventoryDetailsEntity> inquiryProduct(long productId){
        return productInventoryDetailsService.getAvailableProducts(this.productService.getProductById(productId));
    }
    
}
