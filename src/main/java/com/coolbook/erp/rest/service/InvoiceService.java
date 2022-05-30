package com.coolbook.erp.rest.service;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.coolbook.erp.entity.*;
import com.coolbook.erp.repository.InvoiceInventoryDetailsRepository;
import com.coolbook.erp.repository.InvoiceProductRepository;
import com.coolbook.erp.repository.specs.InvoiceSearchSpecification;
import com.coolbook.erp.security.SecurityFacade;
import com.coolbook.erp.security.User;
import com.coolbook.erp.validation.InvoiceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private InvoiceInventoryDetailsRepository invoiceInventoryDetailsRepository;

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
	
	public  long saveInvoice(InvoiceEntity invoice) throws Exception {
	    List<String> validationErrorList=validateInvoice(invoice);
	    if(validationErrorList.isEmpty()) {
            invoice.setUser(userService.getUserById(securityFacade.getCurrentUser().getUserId()));
            long id = this.invoiceRepository.save(invoice).getId();
            invoice.setId(id);
            setInvoiceNumber(invoice, id);
            adjustInventory(invoice);
            return id;
        }else{
	        throw new InvoiceValidationException(validationErrorList);
        }
	    
	}

    public InvoiceEntity getInvoiceById(long id) {
        return this.invoiceRepository.getOne(id);
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
            List<ProductInventoryDetailsEntity> productInventoryDetailsEntities= productInventoryDetailsService.getAvailableProducts(invoiceProductEntity.getProduct());
            double soldQuantity = 0;
            double toBeSold = invoiceProductEntity.getQuantity();
            for(ProductInventoryDetailsEntity productInventoryDetailsEntity:productInventoryDetailsEntities){
                InvoiceInventoryDetailsEntity invoiceInventoryDetails=new InvoiceInventoryDetailsEntity();
                invoiceInventoryDetails.setProductInventoryDetails(productInventoryDetailsEntity);
                double rowQuantity=0;
                if(soldQuantity != toBeSold) {                    
                    if (productInventoryDetailsEntity.getQuantity() <= toBeSold - soldQuantity) {
                        soldQuantity +=rowQuantity= productInventoryDetailsEntity.getQuantity();
                    } else {
                        soldQuantity =rowQuantity= toBeSold - soldQuantity;
                    }
                    invoiceInventoryDetails.setInvoiceId(invoice.getId());
                    invoiceInventoryDetails.setQuantity(rowQuantity);
                    invoiceInventoryDetailsRepository.save(invoiceInventoryDetails);
                    saveInvoiceIncome(invoice, invoiceProductEntity, soldQuantity, productInventoryDetailsEntity,rowQuantity);
                    productInventoryDetailsEntity.setQuantity(productInventoryDetailsEntity.getQuantity() - rowQuantity);
                    productInventoryDetailsService.saveProductInventoryDetails(productInventoryDetailsEntity);
                }else
                    break;
            }
        }
    }

    private void saveInvoiceIncome(InvoiceEntity invoice, InvoiceProductEntity invoiceProductEntity, double soldQuantity, ProductInventoryDetailsEntity productInventoryDetailsEntity,double rowQuantity) {
        InvoiceIncomeEntity invoiceIncomeEntity=new InvoiceIncomeEntity();
        invoiceIncomeEntity.setInvoice(invoice);
        invoiceIncomeEntity.setProduct(productInventoryDetailsEntity.getProduct());
        invoiceIncomeEntity.setQuantity(rowQuantity);
        invoiceIncomeEntity.setCost(productInventoryDetailsEntity.getCost());
        invoiceIncomeEntity.setSalePrice(invoiceProductEntity.getAmount()/invoiceProductEntity.getQuantity());
        this.invoiceIncomeService.saveInvoiceIncome(invoiceIncomeEntity);
    }
    
    public List<String> validateInvoice(InvoiceEntity invoice){
	    List<String> validationErrorList=new ArrayList<>();
	    for(InvoiceProductEntity invoiceProductEntity:invoice.getProducts()){
	        if(invoiceProductEntity.getProduct().getCost() >= invoiceProductEntity.getAmount()){
                validationErrorList.add("Invalid unit Price for "+invoiceProductEntity.getProduct().getProductCode() +": "+invoiceProductEntity.getProduct().getProductName());
            }
	        if(invoiceProductEntity.getProduct().getQuantity() < invoiceProductEntity.getQuantity() || productInventoryDetailsService.getAvailableProducts(invoiceProductEntity.getProduct()).isEmpty()){
                validationErrorList.add("Invalid product quantity for "+invoiceProductEntity.getProduct().getProductCode() +": "+invoiceProductEntity.getProduct().getProductName());
            }
        }
	    return validationErrorList;
    }

    public Page<InvoiceEntity> searchInvoice(Pageable page, String searchValue) {
        InvoiceSearchSpecification specification = new InvoiceSearchSpecification(searchValue);
        return this.invoiceRepository.findAll(specification, page);
    }
    
    public void cancelInvoice(long id){
        InvoiceEntity invoice=this.invoiceRepository.getOne(id);
        for(InvoiceProductEntity invoiceProductEntity:invoice.getProducts()){
            ProductEntity productEntity=invoiceProductEntity.getProduct();
            productEntity.setQuantity(productEntity.getQuantity() + invoiceProductEntity.getQuantity());
            productService.updateProduct(productEntity);
            List<InvoiceInventoryDetailsEntity> invoiceInventoryDetails=invoiceInventoryDetailsRepository.getInvoiceInventoryDetails(invoice.getId());
            for(InvoiceInventoryDetailsEntity invoiceInventoryDetailsEntity:invoiceInventoryDetails){
                ProductInventoryDetailsEntity productInventoryDetailsEntity = invoiceInventoryDetailsEntity.getProductInventoryDetails();
                productInventoryDetailsEntity.setQuantity(productInventoryDetailsEntity.getQuantity()+invoiceInventoryDetailsEntity.getQuantity());
                productInventoryDetailsService.saveProductInventoryDetails(productInventoryDetailsEntity);
            }
        }
        this.invoiceIncomeService.deleteInvoiceIncome(invoice);
        this.invoiceRepository.delete(id);
    }
}
