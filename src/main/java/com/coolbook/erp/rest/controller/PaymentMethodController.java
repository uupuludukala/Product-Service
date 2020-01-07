package com.coolbook.erp.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.ResponseEntity.ok;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coolbook.erp.entity.PaymentMethodEntity;
import com.coolbook.erp.rest.assembler.PaymentMethodAssembler;
import com.coolbook.erp.rest.assembler.PaymentMethodGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.PaymentMethodCriteria;
import com.coolbook.erp.rest.service.PaymentMethodService;
import com.coolbook.model.PaymentMethodGet;
import com.coolbook.model.PaymentMethodPost;

import io.swagger.annotations.ApiParam;

@RestController
public class PaymentMethodController {
	private static final String REFERRER_HEADER_KEY = "referrer";
	PaymentMethodService paymentMethodService;

	@Autowired
	private PagedResourcesAssembler<PaymentMethodEntity> pagedResourcesAssembler;

	@Autowired
	private PaymentMethodGetResourceAssembler assembler;

	@Autowired
	PaymentMethodAssembler paymentMethodAssembler;

	@Autowired
	PaymentMethodController(PaymentMethodService paymentMethodService) {
		this.paymentMethodService = paymentMethodService;
	}

	@RequestMapping(value = "savePaymentMethod", method = RequestMethod.POST)
	public ResponseEntity<Void> savePaymentMethod(@Valid @RequestBody PaymentMethodPost paymentMethod) {
		long paymentMethodId = paymentMethodService.savePaymentMethod(paymentMethodAssembler.essemblePaymentMethodentity(paymentMethod));
		HttpHeaders header = new HttpHeaders();
		header.setLocation(linkTo(ControllerLinkBuilder.methodOn(PaymentMethodController.class).getPaymentMethodById(paymentMethodId)).toUri());
		return new ResponseEntity<>(header, HttpStatus.CREATED);
	}
	
	@RequestMapping(value ="savePaymentMethod/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> updatePaymentMethod(@RequestBody PaymentMethodPost paymentMethod,@ApiParam(value = "PaymentMethod Id", required = true) @PathVariable("id") long id){
		paymentMethodService.updatePaymentMethod(paymentMethodAssembler.essemblePaymentMethodentity(paymentMethod), id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value ="deletePaymentMethod/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletePaymentMethod(@ApiParam(value = "PaymentMethod Id", required = true) @PathVariable("id") long id){
		paymentMethodService.deletePaymentMethod( id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@RequestMapping(value = "getPaymentMethodById/{id}", method = RequestMethod.GET)
	public ResponseEntity<PaymentMethodGet> getPaymentMethodById(@PathVariable("id") long id) {

		return ResponseEntity.ok().body(paymentMethodAssembler.essemblePaymentMethodGet(this.paymentMethodService.getPaymentMethodById(id)));
	}

	@RequestMapping(value = "getAllPaymentMethod", method = RequestMethod.GET)
	public ResponseEntity<PagedResources<PaymentMethodGet>> getAllPaymentMethod(Pageable pageable, HttpServletRequest request,
			@Valid PaymentMethodCriteria criteria) {
		String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
		String basePath = StringUtils.isEmpty(proxyRequestUri)
				? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
				: proxyRequestUri;
		return ok(pagedResourcesAssembler.toResource(this.paymentMethodService.getAllPaymentMethod(pageable, criteria), assembler,
				new Link(basePath)));
	}
	
	
}

