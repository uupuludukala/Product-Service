package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.InvoiceIncomeEntity;
import com.coolbook.erp.repository.InvoiceIncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceIncomeService {
    
    @Autowired
    private InvoiceIncomeRepository invoiceIncomeRepository;
    
    public void saveInvoiceIncome(InvoiceIncomeEntity invoiceIncomeEntity){
        this.invoiceIncomeRepository.save(invoiceIncomeEntity);
        
    }
}
