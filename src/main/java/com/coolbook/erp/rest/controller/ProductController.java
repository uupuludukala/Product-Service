package com.coolbook.erp.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.ResponseEntity.ok;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.coolbook.erp.model.ProductGet;
import com.coolbook.erp.model.ProductPost;
import com.coolbook.erp.rest.searchCriteria.ProductCriteria;
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

import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.rest.assembler.ProductAssembler;
import com.coolbook.erp.rest.assembler.ProductGetResourceAssembler;
import com.coolbook.erp.rest.service.ProductService;

import io.swagger.annotations.ApiParam;

@RestController
public class ProductController {
    
	private static final String REFERRER_HEADER_KEY = "referrer";
	
	@Autowired
	ProductService productService;

	@Autowired
	private PagedResourcesAssembler<ProductEntity> pagedResourcesAssembler;

	@Autowired
	private ProductGetResourceAssembler assembler;

	@Autowired
	private ProductAssembler productAssembler;



	@RequestMapping(value = "saveProduct", method = RequestMethod.POST)
	public ResponseEntity<Void> saveProduct(@Valid @RequestBody ProductPost product) {        
		long productId = productService.saveProduct(productAssembler.essembleProductentity(product));
		HttpHeaders header = new HttpHeaders();
		header.setLocation(linkTo(ControllerLinkBuilder.methodOn(ProductController.class).getProductById(productId)).toUri());		
		return new ResponseEntity<>(header, HttpStatus.CREATED);
	}
	
	@RequestMapping(value ="saveProduct/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateProduct(@RequestBody ProductPost product,@ApiParam(value = "Product Id", required = true) @PathVariable("id") long id){
		productService.updateProduct(productAssembler.essembleProductentity(product), id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value ="deleteProduct/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProduct(@ApiParam(value = "Product Id", required = true) @PathVariable("id") long id){
		productService.deleteProduct( id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@RequestMapping(value = "getProductById/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductGet> getProductById(@PathVariable("id") long id) {

		return ResponseEntity.ok().body(productAssembler.essembleProductGet(this.productService.getProductById(id)));
	}

	@RequestMapping(value = "getAllProduct", method = RequestMethod.GET)
	public ResponseEntity<PagedResources<ProductGet>> getAllProduct(Pageable pageable, HttpServletRequest request,
			@Valid ProductCriteria criteria) {
		String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
		String basePath = StringUtils.isEmpty(proxyRequestUri)
				? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
				: proxyRequestUri;
		return ok(pagedResourcesAssembler.toResource(this.productService.getAllProduct(pageable, criteria), assembler,
				new Link(basePath)));
	}
	
	@RequestMapping(value = "getAllProductPOS", method = RequestMethod.GET)
	public ResponseEntity<PagedResources<ProductGet>> getAllProductPOS(Pageable pageable, HttpServletRequest request,
			@Valid ProductCriteria criteria) {
		String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
		String basePath = StringUtils.isEmpty(proxyRequestUri)
				? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
				: proxyRequestUri;
		return ok(pagedResourcesAssembler.toResource(this.productService.getAllProductPOS(pageable, criteria), assembler,
				new Link(basePath)));
	}
	
	@RequestMapping(value = "searchProduct", method = RequestMethod.GET)
	public ResponseEntity<PagedResources<ProductGet>> searchProduct(Pageable pageable, HttpServletRequest request,
			String searchValue) {
		String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
		String basePath = StringUtils.isEmpty(proxyRequestUri)
				? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
				: proxyRequestUri;
		return ok(pagedResourcesAssembler.toResource(this.productService.searchProduct(pageable, searchValue), assembler,
				new Link(basePath)));
	}
	
}
