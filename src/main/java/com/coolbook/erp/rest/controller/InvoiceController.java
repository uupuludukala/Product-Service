package com.coolbook.erp.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coolbook.erp.rest.assembler.InvoiceAssembler;
import com.coolbook.erp.rest.service.InvoiceService;
import com.coolbook.model.InvoicePost;

@RestController
public class InvoiceController {
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	InvoiceAssembler invoiceAssembler; 

	@RequestMapping(value = "saveInvoice", method = RequestMethod.POST)
	public ResponseEntity<Void> saveCustomer(@Valid @RequestBody InvoicePost invoice) {
		long invoiceId = invoiceService.saveInvoice(invoiceAssembler.essembleInvoiceentity(invoice));
		HttpHeaders header = new HttpHeaders();
		header.setLocation(linkTo(InvoiceController.class).slash(invoiceId).toUri());
		return new ResponseEntity<>(header, HttpStatus.CREATED);
	}
}
