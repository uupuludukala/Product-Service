package com.coolbook.erp.rest.service;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.InvoiceEntity;
import com.coolbook.erp.report.InvoiceReport;
import com.coolbook.erp.repository.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;
//
	@Autowired
	InvoiceReport invoiceReport;
//	@Autowired
//	InvoiceService(InvoiceRepository invoiceRepository,InvoiceReport invoiceReport){
//		this.invoiceRepository=invoiceRepository;
//		this.invoiceReport=invoiceReport;
//	}

	
	public long saveInvoice(InvoiceEntity invoice) {
		return this.invoiceRepository.save(invoice).getId();
	}

	public ByteArrayInputStream invoiceReport() {
		return invoiceReport.generateInvoice();
	}
}
