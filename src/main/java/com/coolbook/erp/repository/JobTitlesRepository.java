package com.coolbook.erp.repository;

import com.coolbook.erp.entity.JobTitlesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobTitlesRepository  extends JpaRepository<JobTitlesEntity, Long>, JpaSpecificationExecutor<JobTitlesEntity> {
}
