package com.coolbook.erp.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.ByteArrayInputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coolbook.erp.model.InvoicePost;
import com.coolbook.erp.rest.assembler.InvoiceAssembler;
import com.coolbook.erp.rest.service.InvoiceService;

@RestController
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	InvoiceAssembler invoiceAssembler;

	@RequestMapping(value = "saveInvoice", method = RequestMethod.POST)
	public ResponseEntity<Void> saveInvoice(@Valid @RequestBody InvoicePost invoice) {
		long invoiceId = invoiceService.saveInvoice(invoiceAssembler.essembleInvoiceentity(invoice));
		HttpHeaders header = new HttpHeaders();
		header.setLocation(linkTo(InvoiceController.class).slash(invoiceId).toUri());
		return new ResponseEntity<>(header, HttpStatus.CREATED);
	}

	
	@RequestMapping(value = "invoiceReport", method = RequestMethod.POST)
	public ResponseEntity invoiceReport() {
		ByteArrayInputStream outStream=invoiceService.invoiceReport();
//		return ResponseEntity.ok().contentType(
//				MediaType.APPLICATION_OCTET_STREAM).body(new InputStreamResource(
//						outStream));	
		
		
		StringBuilder headerContentDispositionValue = new StringBuilder();
		headerContentDispositionValue.append("attachment; filename=test.pdf");

		return ResponseEntity.ok().header(
			"Content-Disposition", headerContentDispositionValue.toString()).contentType(
			MediaType.APPLICATION_OCTET_STREAM).body(new InputStreamResource(outStream));
	}

}
