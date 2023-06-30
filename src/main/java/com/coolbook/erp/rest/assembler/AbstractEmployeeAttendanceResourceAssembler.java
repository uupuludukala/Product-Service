package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.EmployeeAttendanceEntity;
import com.coolbook.erp.model.EmployeeAttendanceGet;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public abstract class AbstractEmployeeAttendanceResourceAssembler extends ResourceAssemblerSupport<EmployeeAttendanceEntity, EmployeeAttendanceGet> {
    protected String requestURI;

    public AbstractEmployeeAttendanceResourceAssembler() {
        super(EmployeeAttendanceEntity.class, EmployeeAttendanceGet.class);
    }

    @Override
    public EmployeeAttendanceGet toResource(EmployeeAttendanceEntity employeeAttendanceEntity) {
        return createEmployeeAttendanceJson(employeeAttendanceEntity);
    }

    private EmployeeAttendanceGet createEmployeeAttendanceJson(EmployeeAttendanceEntity employeeAttendanceEntity) {
        EmployeeAttendanceGet employeeAttendanceGet=new EmployeeAttendanceGet();
        employeeAttendanceGet.setEmployee_attendance_id(employeeAttendanceEntity.getId());
        employeeAttendanceGet.setEmployeeId(employeeAttendanceEntity.getEmployee().getId());
        employeeAttendanceGet.setEmployeeName(employeeAttendanceEntity.getEmployee().getEmployeeName());
        employeeAttendanceGet.setEmployeeMobileNumber(employeeAttendanceEntity.getEmployee().getMobileNumber());
        return employeeAttendanceGet;
    }

    protected abstract String getEmployeeAttendanceSelfLink(String id);
}
