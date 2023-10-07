package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.EmployeeAttendanceEntity;
import com.coolbook.erp.repository.EmployeeAttendanceRepository;
import com.coolbook.erp.repository.specs.EmployeeAttendanceSearchSpecification;
import com.coolbook.erp.repository.specs.EmployeeAttendanceSpecification;
import com.coolbook.erp.rest.searchCriteria.EmployeeAttendanceCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAttendanceService extends BaseService{
    EmployeeAttendanceRepository employeeAttendanceRepository;

    @Autowired
    EmployeeAttendanceService(EmployeeAttendanceRepository employeeAttendanceRepository) {
        this.employeeAttendanceRepository = employeeAttendanceRepository;
    }

    public long saveEmployeeAttendance(EmployeeAttendanceEntity employeeAttendance) {
        setMetaData(employeeAttendance,null);
        return this.employeeAttendanceRepository.save(employeeAttendance).getId();
    }

    public void updateEmployeeAttendance(EmployeeAttendanceEntity employeeAttendance, long id) {
        employeeAttendance.setId(id);
        setMetaData(employeeAttendance,this.employeeAttendanceRepository.getOne(id));
        this.employeeAttendanceRepository.save(employeeAttendance);
    }

    public void deleteEmployeeAttendance(long id) {

        this.employeeAttendanceRepository.delete(id);

    }

    public EmployeeAttendanceEntity getEmployeeAttendanceById(long id) {
        return this.employeeAttendanceRepository.getOne(id);

    }

    public Page<EmployeeAttendanceEntity> getAllEmployeeAttendance(Pageable page, EmployeeAttendanceCriteria searchCriteria) {
        EmployeeAttendanceSpecification specification = new EmployeeAttendanceSpecification(searchCriteria);
        return this.employeeAttendanceRepository.findAll(specification, page);
    }


    public Page<EmployeeAttendanceEntity> searchEmployeeAttendanceByCompany(Pageable page, String searchValue, long employeeId) {
        EmployeeAttendanceSearchSpecification specification = new EmployeeAttendanceSearchSpecification(searchValue, employeeId);
        return this.employeeAttendanceRepository.findAll(specification, page);
    }

    public Page<EmployeeAttendanceEntity> searchEmployeeAttendance(Pageable page, String searchValue) {
        EmployeeAttendanceSearchSpecification specification = new EmployeeAttendanceSearchSpecification(searchValue, 0);
        return this.employeeAttendanceRepository.findAll(specification, page);
    }

}
