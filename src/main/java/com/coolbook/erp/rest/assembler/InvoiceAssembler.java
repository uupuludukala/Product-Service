package com.coolbook.erp.rest.assembler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.coolbook.erp.entity.*;
import com.coolbook.erp.model.InvoiceGet;
import com.coolbook.erp.model.InvoicePost;
import com.coolbook.erp.model.InvoiceProduct;
import com.coolbook.erp.model.InvoiceProductGet;
import com.coolbook.erp.rest.service.BranchService;
import com.coolbook.erp.rest.service.CustomerService;
import com.coolbook.erp.rest.service.ProductService;
import com.coolbook.erp.security.SecurityFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InvoiceAssembler {
    
    DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    
    @Autowired
    private SecurityFacade securityFacade;

    @Autowired
    ProductService productService;
    
    @Autowired
   private BranchService branchService;

    @Autowired
    private CustomerService customerService;
    
    public InvoiceGet assembleInvoiceGet(InvoiceEntity invoice){
        InvoiceGet invoiceGet=new InvoiceGet();
        invoiceGet.setInvoiceNumber(invoice.getInvoiceNumber());
        invoiceGet.setDate(dateFormat2.format(invoice.getDate()));
        invoiceGet.setCustomer(invoice.getCustomer().getCustomerName());
        invoiceGet.setBranch( branchService.getBranchById(invoice.getBranchId()).getCompany().getCompanyName());
        invoiceGet.setProducts(assembleInvoiceProducts(invoice.getProducts()));
        invoiceGet.setTotal(invoice.getTotal());
        invoiceGet.setInvoice_Id(invoice.getId());
        return invoiceGet;
    }
    
    private Set<InvoiceProductGet> assembleInvoiceProducts(Set<InvoiceProductEntity> invoiceProducts){
        Set<InvoiceProductGet> invoiceProductGets=new HashSet();
        for(InvoiceProductEntity invoiceProductEntity:invoiceProducts){
            InvoiceProductGet invoiceProductGet=new InvoiceProductGet();
            invoiceProductGet.setRate(invoiceProductEntity.getRate());
            invoiceProductGet.setQuantity(invoiceProductEntity.getQuantity());
            invoiceProductGet.setDiscount(invoiceProductEntity.getDiscount());
            invoiceProductGet.setDescription(invoiceProductEntity.getDescription());
            invoiceProductGet.setCost(invoiceProductEntity.getCost());
            invoiceProductGet.setAmount(invoiceProductEntity.getAmount());
            invoiceProductGet.setItemCode(invoiceProductEntity.getProduct().getProductCode());
            invoiceProductGets.add(invoiceProductGet);
            
        }
        return invoiceProductGets;
    }
	
	public InvoiceEntity assembleInvoiceEntity(InvoicePost invoicePost) {
		InvoiceEntity invoiceEntity=new InvoiceEntity();
		invoiceEntity.setCashAmount(invoicePost.getCashAmount());
		invoiceEntity.setCustomer(customerService.getCustomerById(invoicePost.getCustomerId()));
		invoiceEntity.setDate(invoicePost.getDate());
		invoiceEntity.setPaymentMethod(invoicePost.getPaymentMethod());
		invoiceEntity.setTotal(invoicePost.getTotal());
		invoiceEntity.setTotalDiscount(invoicePost.getTotalDiscount());
		invoiceEntity.setBranchId(securityFacade.getCurrentUser().getBranchId());
        invoiceEntity.setProducts(assembleInvoiceProductList( invoicePost.getProductList(),invoiceEntity));
		return invoiceEntity;
	}
	
	public Set<InvoiceProductEntity> assembleInvoiceProductList(List<InvoiceProduct> invoiceProductList,InvoiceEntity invoiceEntity) {
		Set<InvoiceProductEntity> invoiceProductEntityList=new HashSet<InvoiceProductEntity>();
		ProductEntity product;
		for(InvoiceProduct invoiceProduct:invoiceProductList) {
			InvoiceProductEntity invoiceProductentity=new InvoiceProductEntity();
            product=productService.getProductById(invoiceProduct.getProductId());
			invoiceProductentity.setProduct(product);
			invoiceProductentity.setRate(invoiceProduct.getRate());
            invoiceProductentity.setTotal(invoiceProduct.getTotal());
            invoiceProductentity.setCost(product.getCost());
			invoiceProductentity.setDiscount(invoiceProduct.getDiscount());
            invoiceProductentity.setAmount(invoiceProduct.getAmount());
            invoiceProductentity.setDescription(invoiceProduct.getDescription());
			invoiceProductentity.setQuantity(invoiceProduct.getQuantity());
            invoiceProductentity.setItemNo(product.getProductCode());
            invoiceProductEntityList.add(invoiceProductentity);
            invoiceProductentity.setInvoice(invoiceEntity);
		}
		return invoiceProductEntityList;
	}

}
