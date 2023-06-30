package com.coolbook.erp.rest.controller;

import com.coolbook.erp.entity.WorkShiftEntity;
import com.coolbook.erp.model.WorkShiftGet;
import com.coolbook.erp.model.WorkShiftPost;
import com.coolbook.erp.rest.assembler.WorkShiftAssembler;
import com.coolbook.erp.rest.assembler.WorkShiftGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.WorkShiftCriteria;
import com.coolbook.erp.rest.service.WorkShiftService;
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
public class WorkShiftController {
    private static final String REFERRER_HEADER_KEY = "referrer";

    WorkShiftService workShiftService;
    WorkShiftAssembler workShiftAssembler;

    @Autowired
    private PagedResourcesAssembler<WorkShiftEntity> pagedResourcesAssembler;

    @Autowired
    private WorkShiftGetResourceAssembler assembler;

    @Autowired
    WorkShiftController(WorkShiftService WorkShiftService, WorkShiftAssembler WorkShiftAssembler) {
        this.workShiftService = WorkShiftService;
        this.workShiftAssembler = WorkShiftAssembler;

    }

    @RequestMapping(value = "saveWorkShift", method = RequestMethod.POST)
    public ResponseEntity<Void> saveWorkShift(@RequestBody @Valid WorkShiftPost WorkShift) {
        long WorkShiftId = workShiftService.saveWorkShift(workShiftAssembler.assembleWorkShiftEntity(WorkShift));
        HttpHeaders header = new HttpHeaders();
        header.setLocation(linkTo(WorkShiftController.class).slash(WorkShiftId).toUri());
        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }


    @RequestMapping(value = "saveWorkShift/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateWorkShift(@RequestBody WorkShiftPost WorkShift, @ApiParam(value = "WorkShift Id", required = true) @PathVariable("id") long id) {
        workShiftService.updateWorkShift(workShiftAssembler.assembleWorkShiftEntity(WorkShift), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "deleteWorkShift/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteWorkShift(@ApiParam(value = "WorkShift Id", required = true) @PathVariable("id") long id) {
        workShiftService.deleteWorkShift(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "getWorkShiftById/{id}", method = RequestMethod.GET)
    public ResponseEntity<WorkShiftGet> getWorkShiftById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(workShiftAssembler.assembleWorkShiftGet(this.workShiftService.getWorkShiftById(id)));
    }

    @RequestMapping(value = "getAllWorkShift", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<WorkShiftGet>> getAllWorkShift(Pageable pageable, HttpServletRequest request, @Valid WorkShiftCriteria searchCriteria) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.workShiftService.getAllWorkShift(pageable, searchCriteria), assembler, new Link(basePath)));
    }


    @RequestMapping(value = "searchWorkShift", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<WorkShiftGet>> searchWorkShift(Pageable pageable, HttpServletRequest request,
                                                                            String searchValue) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.workShiftService.searchWorkShift(pageable, searchValue), assembler,
                new Link(basePath)));
    }
}
