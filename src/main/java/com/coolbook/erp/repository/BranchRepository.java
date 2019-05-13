package com.coolbook.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.coolbook.erp.entity.BranchEntity;

public interface BranchRepository  extends JpaRepository<BranchEntity, Long>,JpaSpecificationExecutor<BranchEntity>{

}
