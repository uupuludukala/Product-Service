package com.coolbook.erp.repository;

import com.coolbook.erp.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BranchRepository  extends JpaRepository<BranchEntity, Long>,JpaSpecificationExecutor<BranchEntity>{

}
