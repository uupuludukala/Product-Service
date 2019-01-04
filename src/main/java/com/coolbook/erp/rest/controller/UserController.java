package com.coolbook.erp.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coolbook.erp.entity.UserEntity;
import com.coolbook.erp.rest.service.UserService;

import io.swagger.annotations.ApiParam;

@RestController
public class UserController {
	UserService userService;
	@Autowired
	UserController(UserService userService){
		this.userService=userService;
	}
	@RequestMapping(value="saveUser",method=RequestMethod.POST)
	public void saveUser(@RequestBody UserEntity user) {
		this.userService.saveUser(user);
	}
	@RequestMapping(value="getUserById/{id}",method=RequestMethod.GET)
	public ResponseEntity<UserEntity> getUserById(@PathVariable ("id") long id){
		return ResponseEntity.ok().body(this.userService.getUserById(id));
	}
	
	@RequestMapping(value="getAllUser",method=RequestMethod.GET)
	public List<UserEntity> getAllUser(){
		return this.userService.getAllUser();
	}
	
	
	@RequestMapping(value="getAllUserByBranchCode/{branchCode}",method=RequestMethod.GET)
	public List<UserEntity> getAllUserByBranchCode(@PathVariable ("branchCode") String branchCode){
		return this.userService.getAllUserByBranchCode(branchCode);
	}
	
}
