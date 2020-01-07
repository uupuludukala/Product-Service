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

import com.coolbook.erp.entity.CustomerEntity;
import com.coolbook.erp.rest.assembler.CustomerAssembler;
import com.coolbook.erp.rest.assembler.CustomerGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.CustomerCriteria;
import com.coolbook.erp.rest.service.CustomerService;
import com.coolbook.model.CustomerGet;
import com.coolbook.model.CustomerPost;

import io.swagger.annotations.ApiParam;

@RestController
public class CustomerController {
	private static final String REFERRER_HEADER_KEY = "referrer";
	CustomerService customerService;

	@Autowired
	private PagedResourcesAssembler<CustomerEntity> pagedResourcesAssembler;

	@Autowired
	private CustomerGetResourceAssembler assembler;

	@Autowired
	CustomerAssembler customerAssembler;

	@Autowired
	CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping(value = "saveCustomer", method = RequestMethod.POST)
	public ResponseEntity<Void> saveCustomer(@Valid @RequestBody CustomerPost customer) {
		long customerId = customerService.saveCustomer(customerAssembler.essembleCustomerentity(customer));
		HttpHeaders header = new HttpHeaders();
		header.setLocation(linkTo(ControllerLinkBuilder.methodOn(CustomerController.class).getCustomerById(customerId)).toUri());
		return new ResponseEntity<>(header, HttpStatus.CREATED);
	}
	
	@RequestMapping(value ="saveCustomer/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateCustomer(@RequestBody CustomerPost customer,@ApiParam(value = "Customer Id", required = true) @PathVariable("id") long id){
		customerService.updateCustomer(customerAssembler.essembleCustomerentity(customer), id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value ="deleteCustomer/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCustomer(@ApiParam(value = "Customer Id", required = true) @PathVariable("id") long id){
		customerService.deleteCustomer( id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@RequestMapping(value = "getCustomerById/{id}", method = RequestMethod.GET)
	public ResponseEntity<CustomerGet> getCustomerById(@PathVariable("id") long id) {

		return ResponseEntity.ok().body(customerAssembler.essembleCustomerGet(this.customerService.getCustomerById(id)));
	}

	@RequestMapping(value = "getAllCustomer", method = RequestMethod.GET)
	public ResponseEntity<PagedResources<CustomerGet>> getAllCustomer(Pageable pageable, HttpServletRequest request,
			@Valid CustomerCriteria criteria) {
		String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
		String basePath = StringUtils.isEmpty(proxyRequestUri)
				? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
				: proxyRequestUri;
		return ok(pagedResourcesAssembler.toResource(this.customerService.getAllCustomer(pageable, criteria), assembler,
				new Link(basePath)));
	}
	
	@RequestMapping(value = "searchCustomer", method = RequestMethod.GET)
	public ResponseEntity<PagedResources<CustomerGet>> searchCustomer(Pageable pageable, HttpServletRequest request,
			String searchValue) {
		String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
		String basePath = StringUtils.isEmpty(proxyRequestUri)
				? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
				: proxyRequestUri;
		return ok(pagedResourcesAssembler.toResource(this.customerService.searchCustomer(pageable, searchValue), assembler,
				new Link(basePath)));
	}
	
	
}
