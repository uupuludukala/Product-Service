package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.EmployeeAttendanceEntity;
import com.coolbook.erp.model.EmployeeAttendanceGet;
import com.coolbook.erp.model.EmployeeAttendancePost;
import com.coolbook.erp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAttendanceAssembler {

    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeAttendanceAssembler(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeAttendanceEntity assembleEmployeeAttendanceEntity(EmployeeAttendancePost employeeAttendancePost) {
        EmployeeAttendanceEntity employeeAttendanceEntity = new EmployeeAttendanceEntity();
        employeeAttendanceEntity.setEmployee(employeeRepository.getOne(employeeAttendancePost.getEmployeeId()));
        employeeAttendanceEntity.setTimeIn(employeeAttendancePost.getTimeIn());
        employeeAttendanceEntity.setTimeOut(employeeAttendancePost.getTimeOut());
        return employeeAttendanceEntity;
    }


    public EmployeeAttendanceGet assembleEmployeeAttendanceGet(EmployeeAttendanceEntity employeeAttendanceEntity) {
        EmployeeAttendanceGet employeeAttendanceGet = new EmployeeAttendanceGet();
        employeeAttendanceGet.setEmployee_attendance_id(employeeAttendanceEntity.getId());
        employeeAttendanceGet.setEmployeeId(employeeAttendanceEntity.getEmployee().getId());
        employeeAttendanceGet.setTimeIn(employeeAttendanceEntity.getTimeIn());
        employeeAttendanceGet.setTimeOut(employeeAttendanceEntity.getTimeOut());
        return employeeAttendanceGet;
    }
}
