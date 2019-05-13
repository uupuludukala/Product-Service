package com.coolbook.erp.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.ResponseEntity.ok;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coolbook.erp.entity.BranchEntity;
import com.coolbook.erp.rest.assembler.BranchAssembler;
import com.coolbook.erp.rest.assembler.BranchGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.BranchCriteria;
import com.coolbook.erp.rest.service.BranchService;
import com.coolbook.model.BranchGet;
import com.coolbook.model.BranchPost;



@RestController
@RequestMapping(value="branchService")
public class BranchController {
	private static final String REFERRER_HEADER_KEY = "referrer";
	
	BranchService branchService;
	BranchAssembler branchAssembler;
	
	@Autowired
	private PagedResourcesAssembler<BranchEntity> pagedResourcesAssembler;
	
	@Autowired
	private BranchGetResourceAssembler assembler;

	@Autowired
	BranchController(BranchService branchService, BranchAssembler branchAssembler) {
		this.branchService = branchService;
		this.branchAssembler = branchAssembler;
		
	}

	@RequestMapping(value = "saveBranch", method = RequestMethod.POST)
	public ResponseEntity<Void> saveBranch(@RequestBody  @Valid BranchPost branch) {
		long branchId=branchService.saveBranch(branchAssembler.essembleBranchEntity(branch));
		HttpHeaders header=new HttpHeaders();
		header.setLocation(linkTo(BranchController.class).slash(branchId).toUri());
		return new ResponseEntity<>(header,HttpStatus.CREATED);
	}

	@RequestMapping(value = "getBranchById/{id}", method = RequestMethod.GET)
	public ResponseEntity<BranchGet> getBranchById(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(branchAssembler.essembleBranchGet(this.branchService.getBranchById(id)));
	}

	@RequestMapping(value = "getAllBranch", method = RequestMethod.GET)
	public ResponseEntity<PagedResources<BranchGet>> getAllBranch(Pageable pageable, HttpServletRequest request,@Valid BranchCriteria searchCriteria) {
		String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
		String basePath = StringUtils.isEmpty(proxyRequestUri)
				? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
				: proxyRequestUri;
		return ok(pagedResourcesAssembler.toResource(this.branchService.getAllBranch(pageable,searchCriteria),assembler,new Link(basePath)));
	}
}
