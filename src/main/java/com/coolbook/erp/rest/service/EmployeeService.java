package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.EmployeeEntity;
import com.coolbook.erp.exception.ConstraintViolationexception;
import com.coolbook.erp.repository.EmployeeRepository;
import com.coolbook.erp.repository.specs.EmployeeSearchSpecification;
import com.coolbook.erp.repository.specs.EmployeeSpecification;
import com.coolbook.erp.rest.searchCriteria.EmployeeCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService extends BaseService{

    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public long saveEmployee(EmployeeEntity employee){
        if (this.employeeRepository.getEmployeeByNIC(employee.getNicNumber()).size() != 0) {
            List<FieldError> fieldErrors=new ArrayList<FieldError>();
            fieldErrors.add(new FieldError("","nicNumber","NIC Number already Exists"));
            throw new ConstraintViolationexception(fieldErrors);
        }
        setMetaData(employee,null);
        return this.employeeRepository.save(employee).getId();

    }

    public void updateEmployee(EmployeeEntity employee, long id) {
        employee.setId(id);
        setMetaData(employee,this.employeeRepository.getOne(id));
        this.employeeRepository.save(employee);
    }

    public void deleteEmployee(long id) {

        this.employeeRepository.delete(id);
    }

    public EmployeeEntity getEmployeeById(long id) {
        return this.employeeRepository.getOne(id);
    }

    public Page<EmployeeEntity> getAllEmployee(Pageable page, EmployeeCriteria criteria) {
        EmployeeSpecification specification = new EmployeeSpecification(criteria);
        return this.employeeRepository.findAll(specification, page);
    }

    public Page<EmployeeEntity> searchEmployee(Pageable page, String searchValue) {
        EmployeeSearchSpecification specification = new EmployeeSearchSpecification(searchValue);
        return this.employeeRepository.findAll(specification, page);
    }
}
