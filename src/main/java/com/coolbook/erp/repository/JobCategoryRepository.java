package com.coolbook.erp.repository;

import com.coolbook.erp.entity.JobCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobCategoryRepository  extends JpaRepository<JobCategoryEntity, Long>, JpaSpecificationExecutor<JobCategoryEntity> {
}
