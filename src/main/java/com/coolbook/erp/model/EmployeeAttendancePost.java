package com.coolbook.erp.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EmployeeAttendancePost {

    private long employeeId;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
}
