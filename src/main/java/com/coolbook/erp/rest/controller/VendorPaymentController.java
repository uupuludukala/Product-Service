package com.coolbook.erp.rest.controller;

import com.coolbook.erp.entity.VendorEntity;
import com.coolbook.erp.entity.VendorPaymentEntity;
import com.coolbook.erp.model.VendorGet;
import com.coolbook.erp.model.VendorPaymentGet;
import com.coolbook.erp.model.VendorPaymentPost;
import com.coolbook.erp.model.VendorPost;
import com.coolbook.erp.rest.assembler.VendorGetResourceAssembler;
import com.coolbook.erp.rest.assembler.VendorPaymentAssembler;
import com.coolbook.erp.rest.assembler.VendorPaymentGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.VendorCriteria;
import com.coolbook.erp.rest.searchCriteria.VendorPaymentCriteria;
import com.coolbook.erp.rest.service.VendorPaymentService;
import io.swagger.annotations.ApiParam;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class VendorPaymentController {

    private static final String REFERRER_HEADER_KEY = "referrer";

    @Autowired
    VendorPaymentAssembler vendorPaymentAssembler;


    @Autowired
    private PagedResourcesAssembler<VendorPaymentEntity> pagedResourcesAssembler;

    @Autowired
    private VendorPaymentGetResourceAssembler assembler;

    @Autowired
    private VendorPaymentService vendorPaymentService;
    
    @RequestMapping(value = "saveVendorPayment", method = RequestMethod.POST)
    public ResponseEntity<Void> saveVendorPayment(@Valid @RequestBody VendorPaymentPost vendorPayment) {
        long vendorId = vendorPaymentService.saveVendorPayment(vendorPaymentAssembler.assembleVendorPaymentEntity(vendorPayment));
        HttpHeaders header = new HttpHeaders();
        header.setLocation(linkTo(ControllerLinkBuilder.methodOn(VendorPaymentController.class).getVendorPaymentById(vendorId)).toUri());
        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }


    @RequestMapping(value = "getVendorPaymentById/{id}", method = RequestMethod.GET)
    public ResponseEntity<VendorPaymentGet> getVendorPaymentById(@PathVariable("id") long id) {

        return ResponseEntity.ok().body(vendorPaymentAssembler.assembleVendorPaymentGet(this.vendorPaymentService.getVendorPaymentById(id)));
    }

    @RequestMapping(value ="saveVendorPayment/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Void> updateVendor(@RequestBody VendorPaymentPost vendorPaymentPost, @ApiParam(value = "Vendor Id", required = true) @PathVariable("id") long id){
        vendorPaymentService.updateVendor(vendorPaymentAssembler.assembleVendorPaymentEntity(vendorPaymentPost), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value ="deleteVendorPayment/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteVendor(@ApiParam(value = "Vendor Id", required = true) @PathVariable("id") long id){
        vendorPaymentService.deleteVendorPayment( id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "getAllVendorPayment", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<VendorPaymentGet>> getAllVendor(Pageable pageable, HttpServletRequest request,
                                                                  @Valid VendorPaymentCriteria criteria) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.vendorPaymentService.getAllVendorPayment(pageable, criteria), assembler,
                new Link(basePath)));
    }

    @RequestMapping(value = "searchVendorPayment", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<VendorPaymentGet>> searchVendor(Pageable pageable, HttpServletRequest request,
                                                                  String searchValue) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.vendorPaymentService.searchVendor(pageable, searchValue), assembler,
                new Link(basePath)));
    }
}
