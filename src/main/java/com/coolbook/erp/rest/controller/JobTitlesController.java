package com.coolbook.erp.rest.controller;

import com.coolbook.erp.entity.JobTitlesEntity;
import com.coolbook.erp.model.JobTitlesGet;
import com.coolbook.erp.model.JobTitlesPost;
import com.coolbook.erp.rest.assembler.JobTitlesAssembler;
import com.coolbook.erp.rest.assembler.JobTitlesGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.JobTitlesCriteria;
import com.coolbook.erp.rest.service.JobTitlesService;
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
public class JobTitlesController {
    private static final String REFERRER_HEADER_KEY = "referrer";

    JobTitlesService jobTitlesService;
    JobTitlesAssembler jobTitlesAssembler;

    @Autowired
    private PagedResourcesAssembler<JobTitlesEntity> pagedResourcesAssembler;

    @Autowired
    private JobTitlesGetResourceAssembler assembler;

    @Autowired
    JobTitlesController(JobTitlesService JobTitlesService, JobTitlesAssembler JobTitlesAssembler) {
        this.jobTitlesService = JobTitlesService;
        this.jobTitlesAssembler = JobTitlesAssembler;

    }

    @RequestMapping(value = "saveJobTitles", method = RequestMethod.POST)
    public ResponseEntity<Void> saveJobTitles(@RequestBody @Valid JobTitlesPost JobTitles) {
        long JobTitlesId = jobTitlesService.saveJobTitles(jobTitlesAssembler.essembleJobTitlesEntity(JobTitles));
        HttpHeaders header = new HttpHeaders();
        header.setLocation(linkTo(JobTitlesController.class).slash(JobTitlesId).toUri());
        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }


    @RequestMapping(value = "saveJobTitles/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateJobTitles(@RequestBody JobTitlesPost JobTitles, @ApiParam(value = "JobTitles Id", required = true) @PathVariable("id") long id) {
        jobTitlesService.updateJobTitles(jobTitlesAssembler.essembleJobTitlesEntity(JobTitles), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "deleteJobTitles/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteJobTitles(@ApiParam(value = "JobTitles Id", required = true) @PathVariable("id") long id) {
        jobTitlesService.deleteJobTitles(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "getJobTitlesById/{id}", method = RequestMethod.GET)
    public ResponseEntity<JobTitlesGet> getJobTitlesById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(jobTitlesAssembler.essembleJobTitlesGet(this.jobTitlesService.getJobTitlesById(id)));
    }

    @RequestMapping(value = "getAllJobTitles", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<JobTitlesGet>> getAllJobTitles(Pageable pageable, HttpServletRequest request, @Valid JobTitlesCriteria searchCriteria) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.jobTitlesService.getAllJobTitles(pageable, searchCriteria), assembler, new Link(basePath)));
    }



    @RequestMapping(value = "searchJobTitles", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<JobTitlesGet>> searchJobTitles(Pageable pageable, HttpServletRequest request,
                                                                        String searchValue) {
        String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
        String basePath = StringUtils.isEmpty(proxyRequestUri)
                ? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
                : proxyRequestUri;
        return ok(pagedResourcesAssembler.toResource(this.jobTitlesService.searchJobTitles(pageable, searchValue), assembler,
                new Link(basePath)));
    }
}
