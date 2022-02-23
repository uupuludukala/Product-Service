package com.coolbook.erp.rest.assembler;

import java.util.*;

import com.coolbook.erp.entity.*;
import com.coolbook.erp.model.InvoicePost;
import com.coolbook.erp.model.InvoiceProduct;
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
    private CustomerService customerService;
	
	public InvoiceEntity essembleInvoiceentity(InvoicePost invoicePost) {
		InvoiceEntity invoiceEntity=new InvoiceEntity();
		invoiceEntity.setCashAmount(invoicePost.getCashAmount());
		invoiceEntity.setCustomer(customerService.getCustomerById(invoicePost.getCustomerId()));
		invoiceEntity.setDate(new Date());
		invoiceEntity.setPaymentMethod(invoicePost.getPaymentMethod());
		invoiceEntity.setTotal(invoicePost.getTotal());
		invoiceEntity.setTotalDiscount(invoicePost.getTotalDiscount());
		return invoiceEntity;
	}
	
	public Set<InvoiceProductEntity> assembleInvoiceProductList(List<InvoiceProduct> invoiceProductList) {
		Set<InvoiceProductEntity> invoiceProductEntityList=new HashSet<InvoiceProductEntity>();
		for(InvoiceProduct invoiceProduct:invoiceProductList) {
			InvoiceProductEntity invocieProductentity=new InvoiceProductEntity();
			invocieProductentity.setProduct(productService.getProductById(invoiceProduct.getProductId()));
			invocieProductentity.setUnitPrice(invoiceProduct.getUnitPrice());
			invocieProductentity.setDiscount(invoiceProduct.getDiscount());
			invocieProductentity.setQuantity(invoiceProduct.getQuantity());
            invoiceProductEntityList.add(invocieProductentity);
		}
		return invoiceProductEntityList;
	}

}
