package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.*;
import com.coolbook.erp.model.*;
import com.coolbook.erp.rest.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractInvoiceResourceAssembler  extends ResourceAssemblerSupport<InvoiceEntity, InvoiceGet> {
    
    protected String requestURI;

    DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");

    public AbstractInvoiceResourceAssembler() {
        super(ProductEntity.class, InvoiceGet.class);
    }

    @Autowired
    private BranchService branchService;

    @Override
    public InvoiceGet toResource(InvoiceEntity  productEntity) {
        return createInvoiceJson(productEntity);
    }

    private InvoiceGet createInvoiceJson(InvoiceEntity  invoiceEntity) {
        InvoiceGet invoiceGet = new InvoiceGet();
        invoiceGet.setInvoiceNumber(invoiceEntity.getInvoiceNumber());
        invoiceGet.setDate(invoiceEntity.getDate()!=null ?dateFormat2.format(invoiceEntity.getDate()):null);
        invoiceGet.setBranch(branchService.getBranchById(invoiceEntity.getBranchId()).getCompany().getCompanyName());
        invoiceGet.setCustomer(invoiceEntity.getCustomer().getCustomerName());
        invoiceGet.setProducts(createProductsJson(invoiceEntity.getProducts()));
        invoiceGet.setInvoice_Id(invoiceEntity.getId());
        return invoiceGet;
    }

    private BranchGet createBranchJson(BranchEntity branchEntity) {
        BranchGet branchGet=new BranchGet();
        branchGet.setCompanyName(branchEntity.getCompany().getCompanyName());
        branchGet.setAddressLine1(branchEntity.getCompany().getAddressLine1());
        branchGet.setAddressLine2(branchEntity.getCompany().getAddressLine2());
        branchGet.setAddressLine3(branchEntity.getCompany().getAddressLine3());
        branchGet.setContactNumber(branchEntity.getCompany().getContactNumber());
        return branchGet;
    }

    private CustomerGet createCustomerJson(CustomerEntity customerEntity) {
        CustomerGet customerGet=new CustomerGet();
        customerGet.setCustomerName(customerEntity.getCustomerName());
        customerGet.setAddressLine1(customerEntity.getAddressLine1());
        customerGet.setAddressLine2(customerEntity.getAddressLine2());
        customerGet.setAddressLine3(customerEntity.getAddressLine3());
        return customerGet;
    }

    private Set<InvoiceProductGet> createProductsJson(Set<InvoiceProductEntity> invoiceProducts) {
        Set<InvoiceProductGet> products=new HashSet<>();
        for(InvoiceProductEntity invoiceProductEntity:invoiceProducts){
            InvoiceProductGet invoiceProductGet=new InvoiceProductGet();
            invoiceProductGet.setAmount(invoiceProductEntity.getAmount());
            invoiceProductGet.setCost(invoiceProductEntity.getCost());
            invoiceProductGet.setDescription(invoiceProductEntity.getDescription());
            invoiceProductGet.setDiscount(invoiceProductEntity.getDiscount());
            invoiceProductGet.setQuantity(invoiceProductEntity.getQuantity());
            invoiceProductGet.setRate(invoiceProductEntity.getRate());
            invoiceProductGet.setTotal(invoiceProductEntity.getTotal());
            products.add(invoiceProductGet);
        }
        return products;
    }

    protected abstract String getSelfLink(String id);
}