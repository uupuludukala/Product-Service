package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.model.UserGet;
import com.coolbook.erp.model.UserPost;
import com.coolbook.erp.common.enums.StatusEnum;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.coolbook.erp.entity.BranchEntity;
import com.coolbook.erp.entity.UserEntity;

@Component
public class UserAssembler {
	public UserGet essembleUserGet(UserEntity userEntity) {
		UserGet userGet = new UserGet();
		userGet.setUser_Id(userEntity.getId());
		userGet.setBranch(userEntity.getBranch().getBranchCode());
		userGet.setBranchId(userEntity.getBranch().getId());
		userGet.setCompanyId(userEntity.getBranch().getCompany().getId());
		userGet.setUserName(userEntity.getUserName());
		userGet.setStatus(StatusEnum.getByCode(userEntity.getStatus()));
		return userGet;
	}

	public UserEntity essembleUserEntity(UserPost userPost) {
		UserEntity userEntity = new UserEntity();
		userEntity.setBranch(new BranchEntity(userPost.getBranch()));
		userEntity.setPassword(BCrypt.hashpw(userPost.getPassword(), BCrypt.gensalt()));
		userEntity.setUserName(userPost.getUserName());
		if(userPost.getStatus()!=null)
            userEntity.setStatus(userPost.getStatus().getCode());
		return userEntity;
	}
}
