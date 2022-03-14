package com.coolbook.erp.rest.service;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;

import com.coolbook.erp.entity.*;
import com.coolbook.erp.repository.InvoiceProductRepository;
import com.coolbook.erp.security.SecurityFacade;
import com.coolbook.erp.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolbook.erp.report.InvoiceReport;
import com.coolbook.erp.repository.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceProductRepository invoiceProductRepository;

	@Autowired
    private InvoiceReport invoiceReport;

    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private InvoiceIncomeService invoiceIncomeService;
    
    @Autowired
    private ProductInventoryDetailsService productInventoryDetailsService;

    @Autowired
    private SecurityFacade securityFacade;
	
	public long saveInvoice(InvoiceEntity invoice) throws Exception {
	    if(validateInvoice(invoice)) {
            invoice.setUser(userService.getUserById(securityFacade.getCurrentUser().getUserId()));
            long id = this.invoiceRepository.save(invoice).getId();
            invoice.setId(id);
            setInvoiceNumber(invoice, id);
            adjustInventory(invoice);
            return id;
        }else{
	        throw new Exception();
        }
	    
	}
	
	public void setInvoiceNumber(InvoiceEntity invoice, long id){
	    invoice.setInvoiceNumber(generateInvoiceNumber(id));
        this.invoiceRepository.save(invoice);
    }

	public ByteArrayInputStream invoiceReport(long id) {
		return invoiceReport.generateInvoice(id);
	}
	
	
	
	public  String generateInvoiceNumber(long id){
	    StringBuilder invoiceNumber=new StringBuilder("");
        User user= securityFacade.getCurrentUser();
        LocalDateTime date = LocalDateTime.now();
        CompanyEntity company=companyService.getCompanyById(user.getCompanyId());
        invoiceNumber.append(company.getCompanyCode()).append("-");
        invoiceNumber.append(user.getBranchCode());
        invoiceNumber.append( date.getYear()).append(date.getMonthValue()).append(date.getDayOfMonth());
        invoiceNumber.append(id);
        return invoiceNumber.toString();
    }
    
    public void adjustInventory(InvoiceEntity invoice){
	    for(InvoiceProductEntity invoiceProductEntity:invoice.getProducts()){
            ProductEntity productEntity=invoiceProductEntity.getProduct();
            productEntity.setQuantity(productEntity.getQuantity() - invoiceProductEntity.getQuantity());
            productService.updateProduct(productEntity);
            List<ProductInventoryDetailsEntity> productInventoryDetailsEntities= productInventoryDetailsService.getOldestProduct(invoiceProductEntity.getProduct());
            double soldQuantity = 0;
            double toBeSold = invoiceProductEntity.getQuantity();
            for(ProductInventoryDetailsEntity productInventoryDetailsEntity:productInventoryDetailsEntities){
                double rowQuantity=0;
                if(soldQuantity != toBeSold) {                    
                    if (productInventoryDetailsEntity.getQuantity() <= toBeSold - soldQuantity) {
                        soldQuantity +=rowQuantity= productInventoryDetailsEntity.getQuantity();
                    } else {
                        soldQuantity =rowQuantity= toBeSold - soldQuantity;
                    }
                    saveInvoiceIncome(invoice, invoiceProductEntity, soldQuantity, productInventoryDetailsEntity,rowQuantity);
                    productInventoryDetailsEntity.setQuantity(productInventoryDetailsEntity.getQuantity() - rowQuantity);
                    productInventoryDetailsService.saveProductInventoryDetails(productInventoryDetailsEntity);
                }
            }
        }
    }

    private void saveInvoiceIncome(InvoiceEntity invoice, InvoiceProductEntity invoiceProductEntity, double soldQuantity, ProductInventoryDetailsEntity productInventoryDetailsEntity,double rowQuantity) {
        InvoiceIncomeEntity invoiceIncomeEntity=new InvoiceIncomeEntity();
        invoiceIncomeEntity.setInvoice(invoice);
        invoiceIncomeEntity.setProduct(productInventoryDetailsEntity.getProduct());
        invoiceIncomeEntity.setQuantity(rowQuantity);
        invoiceIncomeEntity.setCost(productInventoryDetailsEntity.getRate());
        invoiceIncomeEntity.setSalePrice(invoiceProductEntity.getAmount()/invoiceProductEntity.getQuantity());
        this.invoiceIncomeService.saveInvoiceIncome(invoiceIncomeEntity);
    }
    
    public boolean validateInvoice(InvoiceEntity invoice){
	    boolean isValid = true;
	    for(InvoiceProductEntity invoiceProductEntity:invoice.getProducts()){
            isValid=invoiceProductEntity.getProduct().getCost() <= invoiceProductEntity.getUnitPrice() && invoiceProductEntity.getProduct().getQuantity() >= invoiceProductEntity.getQuantity();
            if(!isValid)
                break;
        }
	    return isValid;
    }
}
