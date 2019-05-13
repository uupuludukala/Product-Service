package com.coolbook.erp.rest.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.coolbook.erp.entity.UserEntity;
import com.coolbook.model.UserGet;

public abstract class AbstractUserResourceAssembler extends ResourceAssemblerSupport<UserEntity, UserGet> {
	protected String requestURI;

	public AbstractUserResourceAssembler() {
		super(UserEntity.class, UserGet.class);
	}

	@Override
	public UserGet toResource(UserEntity UserEntity) {
		return createUserJson(UserEntity);
	}

	private UserGet createUserJson(UserEntity UserEntity) {
		UserGet userGet = new UserGet();
		userGet.setUser_Id(UserEntity.getId());
		userGet.setBranch(UserEntity.getBranch().getBranchCode());
		userGet.setUserName(UserEntity.getUserName());
		return userGet;
	}

	protected abstract String getSelfLink(String id);
}