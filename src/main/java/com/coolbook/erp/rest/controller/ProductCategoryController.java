package com.coolbook.erp.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.ResponseEntity.ok;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.coolbook.erp.model.ProductCategoryGet;
import com.coolbook.erp.model.ProductCategoryPost;
import com.coolbook.erp.rest.searchCriteria.ProductCategoryCriteria;
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

import com.coolbook.erp.entity.ProductCategoryEntity;
import com.coolbook.erp.rest.assembler.ProductCategoryAssembler;
import com.coolbook.erp.rest.assembler.ProductCategoryGetResourceAssembler;
import com.coolbook.erp.rest.service.ProductCategoryService;

import io.swagger.annotations.ApiParam;

@RestController
public class ProductCategoryController {
	private static final String REFERRER_HEADER_KEY = "referrer";
	ProductCategoryService productCategoryService;

	@Autowired
	private PagedResourcesAssembler<ProductCategoryEntity> pagedResourcesAssembler;

	@Autowired
	private ProductCategoryGetResourceAssembler assembler;

	@Autowired
	ProductCategoryAssembler productCategoryAssembler;

	@Autowired
	ProductCategoryController(ProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}

	@RequestMapping(value = "saveProductCategory", method = RequestMethod.POST)
	public ResponseEntity<Void> saveProductCategory(@RequestBody ProductCategoryPost productCategory) {
		long productCategoryId = productCategoryService
				.saveProductCategory(productCategoryAssembler.essembleProductCategoryentity(productCategory));
		HttpHeaders header = new HttpHeaders();
		header.setLocation(linkTo(ProductCategoryController.class).slash(productCategoryId).toUri());
		header.setLocation(linkTo(ControllerLinkBuilder.methodOn(ProductCategoryController.class).getProductCategoryById(productCategoryId)).toUri());
		return new ResponseEntity<>(header, HttpStatus.CREATED);

	}

	@RequestMapping(value ="saveProductCategory/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateProductCategory(@RequestBody ProductCategoryPost productCategory,@ApiParam(value = "Product category Id", required = true) @PathVariable("id") long id){
		productCategoryService.updateProductCategory(productCategoryAssembler.essembleProductCategoryentity(productCategory), id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value ="deleteProductCategory/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProductCategory(@ApiParam(value = "Product category Id", required = true) @PathVariable("id") long id){
		productCategoryService.deleteProductCategory(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "getProductCategoryById/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductCategoryGet> getProductCategoryById(@PathVariable("id") long id) {

		return ResponseEntity.ok().body(productCategoryAssembler
				.essembleProductCategoryGet(this.productCategoryService.getProductCategoryById(id)));
	}

	@RequestMapping(value = "getAllProductCategory", method = RequestMethod.GET)
	public ResponseEntity<PagedResources<ProductCategoryGet>> getAllProductCategory(Pageable pageable,
			HttpServletRequest request, @Valid ProductCategoryCriteria criteria) {
		String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
		String basePath = StringUtils.isEmpty(proxyRequestUri)
				? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
				: proxyRequestUri;
		return ok(pagedResourcesAssembler.toResource(
				this.productCategoryService.getAllProductCategory(pageable, criteria), assembler, new Link(basePath)));
	}

    @RequestMapping(value = "searchProductCategory", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<ProductCategoryGet>> searchUser(Pageable pageable, HttpServletRequest request,
                                                              String searchValue) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.productCategoryService.searchProductCategory(pageable, searchValue), assembler,
                new Link(basePath)));
    }
}
