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

import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.model.CompanyGet;
import com.coolbook.erp.model.CompanyPost;
import com.coolbook.erp.rest.assembler.CompanyAssembler;
import com.coolbook.erp.rest.assembler.CompanyGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.CompanyCriteria;
import com.coolbook.erp.rest.service.CompanyService;

@RestController
public class CompanyController {
	private static final String REFERRER_HEADER_KEY = "referrer";
	
	CompanyService companyService;
	
	@Autowired
	private PagedResourcesAssembler<CompanyEntity> pagedResourcesAssembler;
	
	@Autowired
	private CompanyGetResourceAssembler assembler;
	
	@Autowired
	CompanyAssembler companyAssembler;
	
	@Autowired
	CompanyController(CompanyService companyService){
		this.companyService=companyService;
	}
	@RequestMapping(value="saveCompany",method=RequestMethod.POST)
	public ResponseEntity<Void> saveCompany(@RequestBody CompanyPost company) {
		long companyId=companyService.saveCompany(companyAssembler.essembleCompanyentity(company));
		HttpHeaders header=new HttpHeaders();
		header.setLocation(linkTo(BranchController.class).slash(companyId).toUri());
		return new ResponseEntity<>(header,HttpStatus.CREATED);
	}
	@RequestMapping(value="getCompanyById/{id}",method=RequestMethod.GET)
	public ResponseEntity<CompanyGet> getCompanyById(@PathVariable ("id") long id){
		return ResponseEntity.ok().body(companyAssembler.essembleCompanyGet(this.companyService.getCompanyById(id)));
	}
	
	@RequestMapping(value="getAllCompany",method=RequestMethod.GET)
	public ResponseEntity<PagedResources<CompanyGet>> getAllCompany(Pageable pageable, HttpServletRequest request,@Valid CompanyCriteria criteria){

		String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
		String basePath = StringUtils.isEmpty(proxyRequestUri)
				? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
				: proxyRequestUri;
		return ok(pagedResourcesAssembler.toResource(this.companyService.getAllCompany(pageable,criteria),assembler,new Link(basePath)));
	
	}
	
}
