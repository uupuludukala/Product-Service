package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.common.enums.StatusEnum;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.coolbook.erp.entity.UserEntity;
import com.coolbook.erp.model.UserGet;

public abstract class AbstractUserResourceAssembler extends ResourceAssemblerSupport<UserEntity, UserGet> {
	protected String requestURI;

	public AbstractUserResourceAssembler() {
		super(UserEntity.class, UserGet.class);
	}

	@Override
	public UserGet toResource(UserEntity UserEntity) {
		return createUserJson(UserEntity);
	}

	private UserGet createUserJson(UserEntity userEntity) {
		UserGet userGet = new UserGet();
		userGet.setUser_Id(userEntity.getId());
		userGet.setBranch(userEntity.getBranch().getBranchCode());
		userGet.setUserName(userEntity.getUserName());
		userGet.setStatus(StatusEnum.getByCode(userEntity.getStatus()));
		return userGet;
	}

	protected abstract String getSelfLink(String id);
}