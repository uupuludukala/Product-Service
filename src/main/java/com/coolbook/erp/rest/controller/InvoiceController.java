package com.coolbook.erp.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.ResponseEntity.ok;

import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.coolbook.erp.entity.InvoiceEntity;
import com.coolbook.erp.model.InvoiceGet;
import com.coolbook.erp.model.InvoicePost;
import com.coolbook.erp.rest.assembler.InvoiceGetResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.coolbook.erp.rest.assembler.InvoiceAssembler;
import com.coolbook.erp.rest.service.InvoiceService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class InvoiceController {

    private static final String REFERRER_HEADER_KEY = "referrer";
    
	@Autowired
	InvoiceService invoiceService;

    @Autowired
    private PagedResourcesAssembler<InvoiceEntity> pagedResourcesAssembler;

	@Autowired
	InvoiceAssembler invoiceAssembler;

    @Autowired
    private InvoiceGetResourceAssembler assembler;

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

    @RequestMapping(value = "searchInvoice", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<InvoiceGet>> searchInvoice(Pageable pageable, HttpServletRequest request,
                                                                    String searchValue) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.invoiceService.searchInvoice(pageable, searchValue), assembler,
                new Link(basePath)));
    }
}
