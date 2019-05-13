package com.coolbook.erp.rest.assembler;

import org.springframework.stereotype.Component;

import com.coolbook.erp.entity.BranchEntity;
import com.coolbook.erp.entity.UserEntity;
import com.coolbook.model.UserGet;
import com.coolbook.model.UserPost;

@Component
public class UserAssembler {
	public UserGet essembleUserGet(UserEntity userEntity) {
		UserGet userGet = new UserGet();
		userGet.setUser_Id(userEntity.getId());
		userGet.setBranch(userEntity.getBranch().getBranchCode());
		userGet.setUserName(userEntity.getUserName());
		return userGet;
	}

	public UserEntity essembleUserentity(UserPost userPost) {
		UserEntity userEntity = new UserEntity();
		BranchEntity branch=new BranchEntity();
		branch.setId(userPost.getBranch());
		userEntity.setBranch(branch);
		userEntity.setPasword(userPost.getPasword());
		userEntity.setUserName(userPost.getUserName());
		return userEntity;
	}
}
