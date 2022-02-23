package com.coolbook.erp.rest.service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.entity.InvoiceProductEntity;
import com.coolbook.erp.repository.InvoiceProductRepository;
import com.coolbook.erp.security.SecurityFacade;
import com.coolbook.erp.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.InvoiceEntity;
import com.coolbook.erp.report.InvoiceReport;
import com.coolbook.erp.repository.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceProductRepository invoiceProductRepository;

	@Autowired
	InvoiceReport invoiceReport;

    @Autowired
    CompanyService companyService;
    
    @Autowired
    UserService userService;

    @Autowired
    private SecurityFacade securityFacade;
	
	public long saveInvoice(InvoiceEntity invoice, Set<InvoiceProductEntity> invoiceProducts) {
        invoice.setUser(userService.getUserById(securityFacade.getCurrentUser().getUserId()));
	    long id=this.invoiceRepository.save(invoice).getId();
	    for(InvoiceProductEntity invoiceProduct:invoiceProducts){
            invoiceProduct.setInvoice(invoice);
	        this.invoiceProductRepository.save(invoiceProduct);
        }
        invoice.setId(id);
        
        setInvoiceNUmber(invoice,id);
	    return id;
	}
	
	public void setInvoiceNUmber(InvoiceEntity invoice,long id){
	    invoice.setInvoiceNumber(generateInvoiceNumber(id));
        this.invoiceRepository.save(invoice);
    }

	public ByteArrayInputStream invoiceReport() {
		return invoiceReport.generateInvoice();
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
}
