package com.coolbook.erp.rest.assembler;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class EmployeeAttendanceGetResourceAssembler  extends AbstractEmployeeAttendanceResourceAssembler{

    @Override
    protected String getEmployeeAttendanceSelfLink(String id) {
        if (!StringUtils.isEmpty(id)) {
            UriComponentsBuilder builder = ServletUriComponentsBuilder.fromHttpUrl(requestURI).query(null);
            if (!builder.build().toString().endsWith("/employeeAttendances")) {
                String employeeAttendanceCurrentURI = builder.build().toString();
                String employeeAttendanceURI = employeeAttendanceCurrentURI.substring(0, employeeAttendanceCurrentURI.indexOf("/employeeAttendances") + 9);
                builder = ServletUriComponentsBuilder.fromHttpUrl(employeeAttendanceURI);
            }
            return builder.path("/" + id).build().toUriString();
        }
        return ServletUriComponentsBuilder.fromHttpUrl(requestURI).build().toUriString();
    }

}
