package com.coolbook.erp.repository;

import com.coolbook.erp.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>, JpaSpecificationExecutor<EmployeeEntity> {
    @Query(value="from EmployeeEntity c where c.nicNumber =?1")
    ArrayList<EmployeeEntity> getEmployeeByNIC(String nicNumber);

}
