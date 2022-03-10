package com.coolbook.erp.rest.controller;

import com.coolbook.erp.entity.PurchaseOrderEntity;
import com.coolbook.erp.model.PurchaseOrderGet;
import com.coolbook.erp.model.PurchaseOrderPost;
import com.coolbook.erp.rest.assembler.PurchaseOrderAssembler;
import com.coolbook.erp.rest.assembler.PurchaseOrderGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.PurchaseOrderCriteria;
import com.coolbook.erp.rest.service.PurchaseOrderService;
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
public class PurchaseOrderController {
    private static final String REFERRER_HEADER_KEY = "referrer";
    PurchaseOrderService purchaseOrderService;

    @Autowired
    private PagedResourcesAssembler<PurchaseOrderEntity> pagedResourcesAssembler;

    @Autowired
    private PurchaseOrderGetResourceAssembler assembler;

    @Autowired
    PurchaseOrderAssembler purchaseOrderAssembler;

    @Autowired
    PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @RequestMapping(value = "savePurchaseOrder", method = RequestMethod.POST)
    public ResponseEntity<Void> savePurchaseOrder(@Valid @RequestBody PurchaseOrderPost purchaseOrder) {
        long purchaseOrderId = purchaseOrderService.savePurchaseOrder(purchaseOrderAssembler.assemblePurchaseOrderEntity(purchaseOrder));
        HttpHeaders header = new HttpHeaders();
        header.setLocation(linkTo(ControllerLinkBuilder.methodOn(PurchaseOrderController.class).getPurchaseOrderById(purchaseOrderId)).toUri());
        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }

    @RequestMapping(value ="savePurchaseOrder/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Void> updatePurchaseOrder(@RequestBody PurchaseOrderPost purchaseOrder,@ApiParam(value = "PurchaseOrder Id", required = true) @PathVariable("id") long id){
        purchaseOrderService.updatePurchaseOrder(purchaseOrderAssembler.assemblePurchaseOrderEntity(purchaseOrder), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value ="deletePurchaseOrder/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deletePurchaseOrder(@ApiParam(value = "PurchaseOrder Id", required = true) @PathVariable("id") long id){
        purchaseOrderService.deletePurchaseOrder( id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "getPurchaseOrderById/{id}", method = RequestMethod.GET)
    public ResponseEntity<PurchaseOrderGet> getPurchaseOrderById(@PathVariable("id") long id) {

        return ResponseEntity.ok().body(purchaseOrderAssembler.assemblePurchaseOrderGet(this.purchaseOrderService.getPurchaseOrderById(id)));
    }

    @RequestMapping(value = "getAllPurchaseOrder", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<PurchaseOrderGet>> getAllPurchaseOrder(Pageable pageable, HttpServletRequest request,
                                                                  @Valid PurchaseOrderCriteria criteria) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.purchaseOrderService.getAllPurchaseOrder(pageable, criteria), assembler,
                new Link(basePath)));
    }

    @RequestMapping(value = "searchPurchaseOrder", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<PurchaseOrderGet>> searchPurchaseOrder(Pageable pageable, HttpServletRequest request,
                                                                  String searchValue) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.purchaseOrderService.searchPurchaseOrder(pageable, searchValue), assembler,
                new Link(basePath)));
    }


}
