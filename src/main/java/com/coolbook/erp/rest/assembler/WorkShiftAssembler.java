package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.WorkShiftEntity;
import com.coolbook.erp.model.WorkShiftGet;
import com.coolbook.erp.model.WorkShiftPost;
import com.coolbook.erp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkShiftAssembler {

    CompanyRepository companyRepository;

    @Autowired
    WorkShiftAssembler(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public WorkShiftEntity assembleWorkShiftEntity(WorkShiftPost workShiftPost) {
        WorkShiftEntity workShiftEntity = new WorkShiftEntity();
        workShiftEntity.setWorkShiftName(workShiftPost.getWorkShiftName());
        workShiftEntity.setTimeFrom(workShiftPost.getTimeFrom());
        workShiftEntity.setTimeTo(workShiftPost.getTimeTo());
        workShiftEntity.setHoursPerDay(workShiftPost.getHoursPerDay());
        return workShiftEntity;
    }

    public WorkShiftGet assembleWorkShiftGet(WorkShiftEntity workShiftEntity) {
        WorkShiftGet workShiftGet = new WorkShiftGet();
        workShiftGet.setWork_Shift_id(workShiftEntity.getId());
        workShiftGet.setWorkShiftName(workShiftEntity.getWorkShiftName());
        workShiftGet.setTimeFrom(workShiftEntity.getTimeFrom());
        workShiftGet.setTimeTo(workShiftEntity.getTimeTo());
        workShiftGet.setHoursPerDay(workShiftEntity.getHoursPerDay());
        return workShiftGet;
    }
}
