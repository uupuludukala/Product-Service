package com.coolbook.erp.rest.controller;

import com.coolbook.erp.entity.JobCategoryEntity;
import com.coolbook.erp.model.JobCategoryGet;
import com.coolbook.erp.model.JobCategoryPost;
import com.coolbook.erp.rest.assembler.JobCategoryAssembler;
import com.coolbook.erp.rest.assembler.JobCategoryGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.JobCategoryCriteria;
import com.coolbook.erp.rest.service.JobCategoryService;
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
public class JobCategoryController {
    private static final String REFERRER_HEADER_KEY = "referrer";

    JobCategoryService jobCategoryService;
    JobCategoryAssembler jobCategoryAssembler;

    @Autowired
    private PagedResourcesAssembler<JobCategoryEntity> pagedResourcesAssembler;

    @Autowired
    private JobCategoryGetResourceAssembler assembler;

    @Autowired
    JobCategoryController(JobCategoryService JobCategoryService, JobCategoryAssembler JobCategoryAssembler) {
        this.jobCategoryService = JobCategoryService;
        this.jobCategoryAssembler = JobCategoryAssembler;

    }

    @RequestMapping(value = "saveJobCategory", method = RequestMethod.POST)
    public ResponseEntity<Void> saveJobCategory(@RequestBody @Valid JobCategoryPost JobCategory) {
        long JobCategoryId = jobCategoryService.saveJobCategory(jobCategoryAssembler.essembleJobCategoryEntity(JobCategory));
        HttpHeaders header = new HttpHeaders();
        header.setLocation(linkTo(JobCategoryController.class).slash(JobCategoryId).toUri());
        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }


    @RequestMapping(value = "saveJobCategory/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateJobCategory(@RequestBody JobCategoryPost JobCategory, @ApiParam(value = "JobCategory Id", required = true) @PathVariable("id") long id) {
        jobCategoryService.updateJobCategory(jobCategoryAssembler.essembleJobCategoryEntity(JobCategory), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "deleteJobCategory/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteJobCategory(@ApiParam(value = "JobCategory Id", required = true) @PathVariable("id") long id) {
        jobCategoryService.deleteJobCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "getJobCategoryById/{id}", method = RequestMethod.GET)
    public ResponseEntity<JobCategoryGet> getJobCategoryById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(jobCategoryAssembler.essembleJobCategoryGet(this.jobCategoryService.getJobCategoryById(id)));
    }

    @RequestMapping(value = "getAllJobCategory", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<JobCategoryGet>> getAllJobCategory(Pageable pageable, HttpServletRequest request, @Valid JobCategoryCriteria searchCriteria) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.jobCategoryService.getAllJobCategory(pageable, searchCriteria), assembler, new Link(basePath)));
    }


    @RequestMapping(value = "searchJobCategory", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<JobCategoryGet>> searchJobCategory(Pageable pageable, HttpServletRequest request,
                                                                            String searchValue) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.jobCategoryService.searchJobCategory(pageable, searchValue), assembler,
                new Link(basePath)));
    }
}
