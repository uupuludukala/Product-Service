package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.WorkShiftEntity;
import com.coolbook.erp.repository.WorkShiftRepository;
import com.coolbook.erp.repository.specs.WorkShiftSearchSpecification;
import com.coolbook.erp.repository.specs.WorkShiftSpecification;
import com.coolbook.erp.rest.searchCriteria.WorkShiftCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WorkShiftService {
    WorkShiftRepository workShiftRepository;

    @Autowired
    WorkShiftService(WorkShiftRepository workShiftRepository){
        this.workShiftRepository=workShiftRepository;
    }
    public long saveWorkShift(WorkShiftEntity workShift) {
        return this.workShiftRepository.save(workShift).getId();
    }

    public void updateWorkShift(WorkShiftEntity workShift, long id) {
        workShift.setId(id);
        this.workShiftRepository.save(workShift);
    }

    public void deleteWorkShift(long id) {

        this.workShiftRepository.delete(id);

    }
    public WorkShiftEntity getWorkShiftById(long id) {
        return this.workShiftRepository.getOne(id);

    }
    public Page<WorkShiftEntity> getAllWorkShift(Pageable page, WorkShiftCriteria searchCriteria){
        WorkShiftSpecification specification=new WorkShiftSpecification(searchCriteria);
        return this.workShiftRepository.findAll(specification,page);
    }




    public Page<WorkShiftEntity> searchWorkShift(Pageable page, String searchValue) {
        WorkShiftSearchSpecification specification = new WorkShiftSearchSpecification(searchValue);
        return this.workShiftRepository.findAll(specification, page);
    }

}
