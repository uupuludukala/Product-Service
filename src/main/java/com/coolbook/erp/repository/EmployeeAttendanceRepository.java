package com.coolbook.erp.repository;

import com.coolbook.erp.entity.EmployeeAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendanceEntity, Long>, JpaSpecificationExecutor<EmployeeAttendanceEntity> {
}
