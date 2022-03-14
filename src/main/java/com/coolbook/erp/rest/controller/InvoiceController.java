package com.coolbook.erp.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.ByteArrayInputStream;

import javax.validation.Valid;

import com.coolbook.erp.model.InvoicePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coolbook.erp.rest.assembler.InvoiceAssembler;
import com.coolbook.erp.rest.service.InvoiceService;

@RestController
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	InvoiceAssembler invoiceAssembler;

	@RequestMapping(value = "saveInvoice", method = RequestMethod.POST)
	public ResponseEntity<Long> saveInvoice( @RequestBody @Valid InvoicePost invoice) throws Exception {
		long invoiceId = invoiceService.saveInvoice(invoiceAssembler.assembleInvoiceEntity(invoice));
		HttpHeaders header = new HttpHeaders();
        header.add("invoiceId",String.valueOf(invoiceId));
		header.setLocation(linkTo(InvoiceController.class).slash(invoiceId).toUri());
		return new ResponseEntity<>(invoiceId,header, HttpStatus.CREATED);
	}


	
	@RequestMapping(value = "invoiceReport/{id}", method = RequestMethod.GET)
	public ResponseEntity invoiceReport(@PathVariable long  id) {
		ByteArrayInputStream outStream=invoiceService.invoiceReport(id);
		
		StringBuilder headerContentDispositionValue = new StringBuilder();
		headerContentDispositionValue.append("attachment; filename=test.pdf");

		return ResponseEntity.ok().header(
			"Content-Disposition", headerContentDispositionValue.toString()).contentType(
			MediaType.APPLICATION_OCTET_STREAM).body(new InputStreamResource(outStream));
	}

}
