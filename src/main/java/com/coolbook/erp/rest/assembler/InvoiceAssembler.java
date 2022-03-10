package com.coolbook.erp.rest.assembler;

import java.util.*;

import com.coolbook.erp.entity.*;
import com.coolbook.erp.model.InvoicePost;
import com.coolbook.erp.model.InvoiceProduct;
import com.coolbook.erp.rest.service.BranchService;
import com.coolbook.erp.rest.service.CustomerService;
import com.coolbook.erp.rest.service.ProductService;
import com.coolbook.erp.security.SecurityFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InvoiceAssembler {

    @Autowired
    private SecurityFacade securityFacade;

    @Autowired
    ProductService productService;
    
    @Autowired
    BranchService branchService;

    @Autowired
    private CustomerService customerService;
	
	public InvoiceEntity assembleInvoiceEntity(InvoicePost invoicePost) {
		InvoiceEntity invoiceEntity=new InvoiceEntity();
		invoiceEntity.setCashAmount(invoicePost.getCashAmount());
		invoiceEntity.setCustomer(customerService.getCustomerById(invoicePost.getCustomerId()));
		invoiceEntity.setDate(new Date());
		invoiceEntity.setPaymentMethod(invoicePost.getPaymentMethod());
		invoiceEntity.setTotal(invoicePost.getTotal());
		invoiceEntity.setTotalDiscount(invoicePost.getTotalDiscount());
		invoiceEntity.setBranch(branchService.getBranchById(securityFacade.getCurrentUser().getBranchId()));
		return invoiceEntity;
	}
	
	public Set<InvoiceProductEntity> assembleInvoiceProductList(List<InvoiceProduct> invoiceProductList) {
		Set<InvoiceProductEntity> invoiceProductEntityList=new HashSet<InvoiceProductEntity>();
		ProductEntity product;
		for(InvoiceProduct invoiceProduct:invoiceProductList) {
			InvoiceProductEntity invoiceProductentity=new InvoiceProductEntity();
            product=productService.getProductById(invoiceProduct.getProductId());
			invoiceProductentity.setProduct(product);
			invoiceProductentity.setUnitPrice(invoiceProduct.getUnitPrice());
            invoiceProductentity.setCost(product.getCost());
			invoiceProductentity.setDiscount(invoiceProduct.getDiscount());
            invoiceProductentity.setAmount(invoiceProduct.getAmount());
            invoiceProductentity.setDescription(product.getProductName());
			invoiceProductentity.setQuantity(invoiceProduct.getQuantity());
            invoiceProductentity.setItemNo(product.getProductCode());
            invoiceProductEntityList.add(invoiceProductentity);
		}
		return invoiceProductEntityList;
	}

}
