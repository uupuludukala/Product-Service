package com.coolbook.erp.repository;

import com.coolbook.erp.entity.WorkShiftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WorkShiftRepository extends JpaRepository<WorkShiftEntity, Long>, JpaSpecificationExecutor<WorkShiftEntity> {
}
