package com.coolbook.erp.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coolbook.erp.entity.UserEntity;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity>{
	@Query(value="from UserEntity u where u.branch.branchCode =?1")
	ArrayList<UserEntity> getUserByBranch(String companyCode);

}
