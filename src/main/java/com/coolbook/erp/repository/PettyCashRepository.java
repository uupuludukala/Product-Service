package com.coolbook.erp.repository;

import com.coolbook.erp.entity.PettyCashEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PettyCashRepository  extends JpaRepository<PettyCashEntity, Long>, JpaSpecificationExecutor<PettyCashEntity> {
}
