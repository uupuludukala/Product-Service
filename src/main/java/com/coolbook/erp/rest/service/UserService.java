package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.BranchEntity;
import com.coolbook.erp.repository.specs.BranchSearchSpecification;
import com.coolbook.erp.repository.specs.UserSearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.ProductEntity;
import com.coolbook.erp.entity.UserEntity;
import com.coolbook.erp.repository.UserRepository;
import com.coolbook.erp.repository.specs.UserSpecification;
import com.coolbook.erp.rest.searchCriteria.UserCriteria;

@Service
public class UserService {
UserRepository userRepository;
	
	@Autowired
	UserService(UserRepository userRepository){
		this.userRepository=userRepository;
	}
	public long saveUser(UserEntity user) {
		return this.userRepository.save(user).getId();
	}
	public UserEntity getUserById(long id) {
		return this.userRepository.getOne(id);
	}
	public Page<UserEntity> getAllUser(Pageable page, UserCriteria criteria){
		UserSpecification specification = new UserSpecification(criteria);
		return this.userRepository.findAll(specification, page);
	}
	
	public void updateUser(UserEntity user,long id) {
		user.setId(id);
		this.userRepository.save(user);
	}
	
	public void deleteUser(long id) {
		
		this.userRepository.delete(id);
	}

    public Page<UserEntity> searchBranch(Pageable page, String searchValue) {
        UserSearchSpecification specification = new UserSearchSpecification(searchValue,0);
        return this.userRepository.findAll(specification, page);
    }
	
}
