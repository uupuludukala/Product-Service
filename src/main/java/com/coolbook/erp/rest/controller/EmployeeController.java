package com.coolbook.erp.rest.controller;

import com.coolbook.erp.entity.EmployeeEntity;
import com.coolbook.erp.model.EmployeeGet;
import com.coolbook.erp.model.EmployeePost;
import com.coolbook.erp.rest.assembler.EmployeeAssembler;
import com.coolbook.erp.rest.assembler.EmployeeGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.EmployeeCriteria;
import com.coolbook.erp.rest.service.EmployeeService;
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
public class EmployeeController {
    private static final String REFERRER_HEADER_KEY = "referrer";
    EmployeeService employeeService;

    @Autowired
    private PagedResourcesAssembler<EmployeeEntity> pagedResourcesAssembler;

    @Autowired
    private EmployeeGetResourceAssembler assembler;

    @Autowired
    EmployeeAssembler employeeAssembler;

    @Autowired
    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "saveEmployee", method = RequestMethod.POST)
    public ResponseEntity<Void> saveEmployee(@Valid @RequestBody EmployeePost employee) {
        long employeeId = employeeService.saveEmployee(employeeAssembler.essembleEmployeeEntity(employee));
        HttpHeaders header = new HttpHeaders();
        header.setLocation(linkTo(ControllerLinkBuilder.methodOn(EmployeeController.class).getEmployeeById(employeeId)).toUri());
        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }

    @RequestMapping(value ="saveEmployee/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Void> updateEmployee(@RequestBody EmployeePost employee,@ApiParam(value = "Employee Id", required = true) @PathVariable("id") long id){
        employeeService.updateEmployee(employeeAssembler.essembleEmployeeEntity(employee), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value ="deleteEmployee/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEmployee(@ApiParam(value = "Employee Id", required = true) @PathVariable("id") long id){
        employeeService.deleteEmployee( id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "getEmployeeById/{id}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeGet> getEmployeeById(@PathVariable("id") long id) {

        return ResponseEntity.ok().body(employeeAssembler.essembleEmployeeGet(this.employeeService.getEmployeeById(id)));
    }

    @RequestMapping(value = "getAllEmployee", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<EmployeeGet>> getAllEmployee(Pageable pageable, HttpServletRequest request,
                                                                      @Valid EmployeeCriteria criteria) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.employeeService.getAllEmployee(pageable, criteria), assembler,
                new Link(basePath)));
    }

    @RequestMapping(value = "searchEmployee", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<EmployeeGet>> searchEmployee(Pageable pageable, HttpServletRequest request,
                                                                      String searchValue) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.employeeService.searchEmployee(pageable, searchValue), assembler,
                new Link(basePath)));
    }


}
