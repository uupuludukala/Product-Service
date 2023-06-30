package com.coolbook.erp.rest.controller;

import com.coolbook.erp.entity.EmployeeAttendanceEntity;
import com.coolbook.erp.model.EmployeeAttendanceGet;
import com.coolbook.erp.model.EmployeeAttendancePost;
import com.coolbook.erp.rest.assembler.EmployeeAttendanceAssembler;
import com.coolbook.erp.rest.assembler.EmployeeAttendanceGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.EmployeeAttendanceCriteria;
import com.coolbook.erp.rest.service.EmployeeAttendanceService;
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
public class EmployeeAttendanceController {
    private static final String REFERRER_HEADER_KEY = "referrer";

    EmployeeAttendanceService employeeAttendanceService;
    EmployeeAttendanceAssembler employeeAttendanceAssembler;

    @Autowired
    private PagedResourcesAssembler<EmployeeAttendanceEntity> pagedResourcesAssembler;

    @Autowired
    private EmployeeAttendanceGetResourceAssembler assembler;

    @Autowired
    EmployeeAttendanceController(EmployeeAttendanceService employeeAttendanceService, EmployeeAttendanceAssembler employeeAttendanceAssembler) {
        this.employeeAttendanceService = employeeAttendanceService;
        this.employeeAttendanceAssembler = employeeAttendanceAssembler;

    }

    @RequestMapping(value = "saveEmployeeAttendance", method = RequestMethod.POST)
    public ResponseEntity<Void> saveEmployeeAttendance(@RequestBody @Valid EmployeeAttendancePost employeeAttendance) {
        long employeeAttendanceId=employeeAttendanceService.saveEmployeeAttendance(employeeAttendanceAssembler.assembleEmployeeAttendanceEntity(employeeAttendance));
        HttpHeaders header=new HttpHeaders();
        header.setLocation(linkTo(EmployeeAttendanceController.class).slash(employeeAttendanceId).toUri());
        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }


    @RequestMapping(value ="saveEmployeeAttendance/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Void> updateEmployeeAttendance(@RequestBody EmployeeAttendancePost employeeAttendance,@ApiParam(value = "EmployeeAttendance Id", required = true) @PathVariable("id") long id){
        employeeAttendanceService.updateEmployeeAttendance(employeeAttendanceAssembler.assembleEmployeeAttendanceEntity(employeeAttendance), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value ="deleteEmployeeAttendance/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEmployeeAttendance(@ApiParam(value = "EmployeeAttendance Id", required = true) @PathVariable("id") long id){
        employeeAttendanceService.deleteEmployeeAttendance( id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "getEmployeeAttendanceById/{id}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeAttendanceGet> getEmployeeAttendanceById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(employeeAttendanceAssembler.assembleEmployeeAttendanceGet(this.employeeAttendanceService.getEmployeeAttendanceById(id)));
    }

    @RequestMapping(value = "getAllEmployeeAttendance", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<EmployeeAttendanceGet>> getAllEmployeeAttendance(Pageable pageable, HttpServletRequest request, @Valid EmployeeAttendanceCriteria searchCriteria) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.employeeAttendanceService.getAllEmployeeAttendance(pageable,searchCriteria),assembler,new Link(basePath)));
    }

    @RequestMapping(value = "searchEmployeeAttendanceByCompany", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<EmployeeAttendanceGet>> searchEmployeeAttendanceByCompany(Pageable pageable, HttpServletRequest request,
                                                                           String searchValue,long companyId) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.employeeAttendanceService.searchEmployeeAttendanceByCompany(pageable, searchValue,companyId), assembler,
                new Link(basePath)));
    }

    @RequestMapping(value = "searchEmployeeAttendance", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<EmployeeAttendanceGet>> searchEmployeeAttendance(Pageable pageable, HttpServletRequest request,
                                                                  String searchValue) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.employeeAttendanceService.searchEmployeeAttendance(pageable, searchValue), assembler,
                new Link(basePath)));
    }
}
