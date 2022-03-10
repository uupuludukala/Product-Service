package com.coolbook.erp.rest.controller;

import com.coolbook.erp.entity.VendorEntity;
import com.coolbook.erp.model.VendorGet;
import com.coolbook.erp.model.VendorPost;
import com.coolbook.erp.rest.assembler.VendorAssembler;
import com.coolbook.erp.rest.assembler.VendorGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.VendorCriteria;
import com.coolbook.erp.rest.service.VendorService;
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
public class VendorController {
    private static final String REFERRER_HEADER_KEY = "referrer";
    VendorService vendorService;

    @Autowired
    private PagedResourcesAssembler<VendorEntity> pagedResourcesAssembler;

    @Autowired
    private VendorGetResourceAssembler assembler;

    @Autowired
    VendorAssembler vendorAssembler;

    @Autowired
    VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @RequestMapping(value = "saveVendor", method = RequestMethod.POST)
    public ResponseEntity<Void> saveVendor(@Valid @RequestBody VendorPost vendor) {
        long vendorId = vendorService.saveVendor(vendorAssembler.essembleVendorEntity(vendor));
        HttpHeaders header = new HttpHeaders();
        header.setLocation(linkTo(ControllerLinkBuilder.methodOn(VendorController.class).getVendorById(vendorId)).toUri());
        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }

    @RequestMapping(value ="saveVendor/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Void> updateVendor(@RequestBody VendorPost vendor,@ApiParam(value = "Vendor Id", required = true) @PathVariable("id") long id){
        vendorService.updateVendor(vendorAssembler.essembleVendorEntity(vendor), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value ="deleteVendor/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteVendor(@ApiParam(value = "Vendor Id", required = true) @PathVariable("id") long id){
        vendorService.deleteVendor( id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "getVendorById/{id}", method = RequestMethod.GET)
    public ResponseEntity<VendorGet> getVendorById(@PathVariable("id") long id) {

        return ResponseEntity.ok().body(vendorAssembler.assembleVendorGet(this.vendorService.getVendorById(id)));
    }

    @RequestMapping(value = "getAllVendor", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<VendorGet>> getAllVendor(Pageable pageable, HttpServletRequest request,
                                                                      @Valid VendorCriteria criteria) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.vendorService.getAllVendor(pageable, criteria), assembler,
                new Link(basePath)));
    }

    @RequestMapping(value = "searchVendor", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<VendorGet>> searchVendor(Pageable pageable, HttpServletRequest request,
                                                                      String searchValue) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.vendorService.searchVendor(pageable, searchValue), assembler,
                new Link(basePath)));
    }


}
