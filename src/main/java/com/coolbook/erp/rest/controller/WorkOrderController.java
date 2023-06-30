package com.coolbook.erp.rest.controller;

import com.coolbook.erp.entity.WorkOrderEntity;
import com.coolbook.erp.model.WorkOrderGet;
import com.coolbook.erp.model.WorkOrderPost;
import com.coolbook.erp.rest.assembler.WorkOrderAssembler;
import com.coolbook.erp.rest.assembler.WorkOrderGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.WorkOrderCriteria;
import com.coolbook.erp.rest.service.WorkOrderService;
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
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class WorkOrderController {
    private static final String REFERRER_HEADER_KEY = "referrer";

    WorkOrderService workOrderService;

    @Autowired
    private PagedResourcesAssembler<WorkOrderEntity> pagedResourcesAssembler;

    @Autowired
    private WorkOrderGetResourceAssembler assembler;

    @Autowired
    WorkOrderAssembler workOrderAssembler;



    @Autowired
    WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    @RequestMapping(value = "saveWorkOrder", method = RequestMethod.POST)
    public ResponseEntity<Void> saveWorkOrder(@Valid @RequestBody WorkOrderPost workOrder) {
        long workOrderId = workOrderService.saveWorkOrder(workOrderAssembler.essembleWorkOrderEntity(workOrder));
        HttpHeaders header = new HttpHeaders();
        header.setLocation(linkTo(ControllerLinkBuilder.methodOn(WorkOrderController.class).getWorkOrderById(workOrderId)).toUri());
        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }



    @RequestMapping(value ="saveWorkOrder/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Void> updateWorkOrder(@RequestBody WorkOrderPost workOrder,@ApiParam(value = "WorkOrder Id", required = true) @PathVariable("id") long id){
        workOrderService.updateWorkOrder(workOrderAssembler.essembleWorkOrderEntity(workOrder), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value ="deleteWorkOrder/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteWorkOrder(@ApiParam(value = "WorkOrder Id", required = true) @PathVariable("id") long id){
        workOrderService.deleteWorkOrder( id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "getWorkOrderById/{id}", method = RequestMethod.GET)
    public ResponseEntity<WorkOrderGet> getWorkOrderById(@PathVariable("id") long id) {

        return ResponseEntity.ok().body(workOrderAssembler.assembleWorkOrderGet(this.workOrderService.getWorkOrderById(id)));
    }


    @RequestMapping(value = "getAllWorkOrder", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<WorkOrderGet>> getAllWorkOrder(Pageable pageable, HttpServletRequest request,
                                                                  @Valid WorkOrderCriteria criteria) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.workOrderService.getAllWorkOrder(pageable, criteria), assembler,
                new Link(basePath)));
    }

    @RequestMapping(value = "searchWorkOrder", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<WorkOrderGet>> searchWorkOrder(Pageable pageable, HttpServletRequest request,
                                                                  String searchValue) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.workOrderService.searchWorkOrder(pageable, searchValue), assembler,
                new Link(basePath)));
    }


}
