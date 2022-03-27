package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.InvoiceEntity;
import com.coolbook.erp.entity.InvoiceIncomeEntity;
import com.coolbook.erp.repository.InvoiceIncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceIncomeService {
    
    @Autowired
    private InvoiceIncomeRepository invoiceIncomeRepository;
    
    public void saveInvoiceIncome(InvoiceIncomeEntity invoiceIncomeEntity){
        this.invoiceIncomeRepository.save(invoiceIncomeEntity);
    }
    
    public void deleteInvoiceIncome(InvoiceEntity invoice){
        List<InvoiceIncomeEntity>  invoiceIncomeEntities = this.invoiceIncomeRepository.getInvoiceIncomesByInvoice(invoice);
        for(InvoiceIncomeEntity invoiceIncomeEntity:invoiceIncomeEntities){
            this.invoiceIncomeRepository.delete(invoiceIncomeEntity);    
        }
        
    }
}
