package com.coolbook.erp.rest.assembler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coolbook.erp.model.InvoicePost;
import com.coolbook.erp.model.InvoiceProduct;
import org.springframework.stereotype.Component;

import com.coolbook.erp.entity.CustomerEntity;
import com.coolbook.erp.entity.InvoiceEntity;
import com.coolbook.erp.entity.InvoiceProductEntity;
import com.coolbook.erp.entity.ProductEntity;


@Component
public class InvoiceAssembler {
	
	public InvoiceEntity essembleInvoiceentity(InvoicePost invoicePost) {
		InvoiceEntity invoiceEntity=new InvoiceEntity();
		invoiceEntity.setProducts(asssembleInvoiceProductList(invoicePost.getProductList()));
		invoiceEntity.setCashAmount(invoicePost.getCashAmount());
		invoiceEntity.setCustomer(new CustomerEntity(invoicePost.getCustomerId()));
		invoiceEntity.setDate(new Date());
		invoiceEntity.setPaymentMethod(invoicePost.getPaymentMethod());
		invoiceEntity.setTotal(invoicePost.getTotal());
		invoiceEntity.setTotalDiscount(invoicePost.getTotalDiscount());
//		invoiceEntity.setUser();
		return invoiceEntity;
	}
	
	public List<InvoiceProductEntity> asssembleInvoiceProductList(List<InvoiceProduct> invoiceProductList) {
		List<InvoiceProductEntity> invoiceProductEntityList=new ArrayList<InvoiceProductEntity>();
		for(InvoiceProduct invoiceProduct:invoiceProductList) {
			InvoiceProductEntity invocieProductentity=new InvoiceProductEntity();
			invocieProductentity.setProduct(new ProductEntity(invoiceProduct.getProductId()));
			invocieProductentity.setUnitPrice(invoiceProduct.getUnitPrice());
			invocieProductentity.setDiscount(invoiceProduct.getDiscount());
			invocieProductentity.setQuantity(invoiceProduct.getQuantity());
		}
		return invoiceProductEntityList;
	}

}
