package com.coolbook.erp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;

@Data
public class EmployeeAttendanceGet  extends ResourceSupport {

    @JsonProperty("id")
    private long employee_attendance_id;
    private long employeeId;
    private String employeeName;
    private String employeeMobileNumber;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
}
