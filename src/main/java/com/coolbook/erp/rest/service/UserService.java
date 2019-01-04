package com.coolbook.erp.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.UserEntity;
import com.coolbook.erp.repository.UserRepository;

@Service
public class UserService {
UserRepository userRepository;
	
	@Autowired
	UserService(UserRepository userRepository){
		this.userRepository=userRepository;
	}
	public void saveUser(UserEntity user) {
		this.userRepository.save(user);
	}
	public UserEntity getUserById(long id) {
		return this.userRepository.getOne(id);
	}
	public List<UserEntity> getAllUser(){
		return this.userRepository.findAll();
	}
	
	public List<UserEntity> getAllUserByBranchCode(String branchCode){
		return this.userRepository.getUserByBranch(branchCode);
	}
}
