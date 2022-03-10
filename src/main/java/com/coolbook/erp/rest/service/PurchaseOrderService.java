package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.PurchaseOrderEntity;
import com.coolbook.erp.entity.PurchaseOrderProductEntity;
import com.coolbook.erp.repository.PurchaseOrderProductRepository;
import com.coolbook.erp.repository.PurchaseOrderRepository;
import com.coolbook.erp.repository.specs.PurchaseOrderSearchSpecification;
import com.coolbook.erp.repository.specs.PurchaseOrderSpecification;
import com.coolbook.erp.rest.searchCriteria.PurchaseOrderCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PurchaseOrderProductRepository purchaseOrderAccountRepository;


    public long savePurchaseOrder(PurchaseOrderEntity purchaseOrder){
        long id=this.purchaseOrderRepository.save(purchaseOrder).getId();
        return id;
    }

    public void updatePurchaseOrder(PurchaseOrderEntity purchaseOrder, long id) {
        purchaseOrder.setId(id);
        this.purchaseOrderRepository.save(purchaseOrder);
    }

    public void deletePurchaseOrder(long id) {

        this.purchaseOrderRepository.delete(id);
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
}
