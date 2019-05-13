package com.coolbook.erp.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.InvoiceEntity;
import com.coolbook.erp.repository.InvoiceRepository;


@Service
public class InvoiceService {
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	public long saveInvoice(InvoiceEntity invoice) {
		return this.invoiceRepository.save(invoice).getId();
	}
}
