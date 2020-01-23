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

import com.coolbook.erp.entity.UserEntity;
import com.coolbook.erp.model.UserGet;
import com.coolbook.erp.model.UserPost;
import com.coolbook.erp.rest.assembler.UserAssembler;
import com.coolbook.erp.rest.assembler.UserGetResourceAssembler;
import com.coolbook.erp.rest.searchCriteria.UserCriteria;
import com.coolbook.erp.rest.service.UserService;

import io.swagger.annotations.ApiParam;

@RestController
public class UserController {
	private static final String REFERRER_HEADER_KEY = "referrer";
	UserService userService;

	@Autowired
	private PagedResourcesAssembler<UserEntity> pagedResourcesAssembler;

	@Autowired
	private UserGetResourceAssembler assembler;

	@Autowired
	UserAssembler userAssembler;

	@Autowired
	UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "saveUser", method = RequestMethod.POST)
	public ResponseEntity<Void> saveUser(@RequestBody UserPost user) {
		long userId = userService.saveUser(userAssembler.essembleUserEntity(user));
		HttpHeaders header = new HttpHeaders();
		header.setLocation(linkTo(UserController.class).slash(userId).toUri());
		return new ResponseEntity<>(header, HttpStatus.CREATED);
	}

	@RequestMapping(value = "getUserById/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserGet> getUserById(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(userAssembler.essembleUserGet(this.userService.getUserById(id)));
	}

	@RequestMapping(value = "getAllUser", method = RequestMethod.GET)
	public ResponseEntity<PagedResources<UserGet>> getAllUser(Pageable pageable, HttpServletRequest request,
			@Valid UserCriteria criteria) {
		String proxyRequestUri = request.getHeader(REFERRER_HEADER_KEY);
		String basePath = StringUtils.isEmpty(proxyRequestUri)
				? ServletUriComponentsBuilder.fromCurrentRequest().toUriString()
				: proxyRequestUri;
		return ok(pagedResourcesAssembler.toResource(this.userService.getAllUser(pageable, criteria), assembler,
				new Link(basePath)));
	}
	
	@RequestMapping(value ="deleteUser/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@ApiParam(value = "User Id", required = true) @PathVariable("id") long id){
		userService.deleteUser( id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value ="saveUser/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateUser(@RequestBody UserPost user,@ApiParam(value = "User Id", required = true) @PathVariable("id") long id){
		userService.updateUser(userAssembler.essembleUserEntity(user), id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
