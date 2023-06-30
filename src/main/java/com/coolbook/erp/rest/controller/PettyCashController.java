package com.coolbook.erp.rest.controller;

import com.coolbook.erp.entity.PettyCashEntity;
import com.coolbook.erp.model.PettyCashGet;
import com.coolbook.erp.model.PettyCashPost;
import com.coolbook.erp.rest.assembler.PettyCashAssembler;
import com.coolbook.erp.rest.assembler.PettyCashGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.PettyCashCriteria;
import com.coolbook.erp.rest.service.PettyCashService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
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
public class PettyCashController {
    private static final String REFERRER_HEADER_KEY = "referrer";

    PettyCashService pettyCashService;
    PettyCashAssembler pettyCashAssembler;

    @Autowired
    private PagedResourcesAssembler<PettyCashEntity> pagedResourcesAssembler;

    @Autowired
    private PettyCashGetResourceAssembler assembler;

    @Autowired
    PettyCashController(PettyCashService pettyCashService, PettyCashAssembler pettyCashAssembler) {
        this.pettyCashService = pettyCashService;
        this.pettyCashAssembler = pettyCashAssembler;

    }

    @RequestMapping(value = "savePettyCash", method = RequestMethod.POST)
    public ResponseEntity<Void> savePettyCash(@RequestBody @Valid PettyCashPost pettyCash) {
        long pettyCashId=pettyCashService.savePettyCash(pettyCashAssembler.essemblePettyCashEntity(pettyCash));
        HttpHeaders header=new HttpHeaders();
        header.setLocation(linkTo(PettyCashController.class).slash(pettyCashId).toUri());
        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }


    @RequestMapping(value ="savePettyCash/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Void> updatePettyCash(@RequestBody PettyCashPost pettyCash,@ApiParam(value = "PettyCash Id", required = true) @PathVariable("id") long id){
        pettyCashService.updatePettyCash(pettyCashAssembler.essemblePettyCashEntity(pettyCash), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value ="deletePettyCash/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deletePettyCash(@ApiParam(value = "PettyCash Id", required = true) @PathVariable("id") long id){
        pettyCashService.deletePettyCash( id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "getPettyCashById/{id}", method = RequestMethod.GET)
    public ResponseEntity<PettyCashGet> getPettyCashById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(pettyCashAssembler.essemblePettyCashGet(this.pettyCashService.getPettyCashById(id)));
    }

    @RequestMapping(value = "getAllPettyCash", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<PettyCashGet>> getAllPettyCash(Pageable pageable, HttpServletRequest request, @Valid PettyCashCriteria searchCriteria) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.pettyCashService.getAllPettyCash(pageable,searchCriteria),assembler,new Link(basePath)));
    }

    @RequestMapping(value = "searchPettyCashByCompany", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<PettyCashGet>> searchPettyCashByCompany(Pageable pageable, HttpServletRequest request,
                                                                           String searchValue,long companyId) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.pettyCashService.searchPettyCashByCompany(pageable, searchValue,companyId), assembler,
                new Link(basePath)));
    }

    @RequestMapping(value = "searchPettyCash", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<PettyCashGet>> searchPettyCash(Pageable pageable, HttpServletRequest request,
                                                                  String searchValue) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.pettyCashService.searchPettyCash(pageable, searchValue), assembler,
                new Link(basePath)));
    }
}
