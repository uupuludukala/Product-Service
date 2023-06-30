package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.WorkShiftEntity;
import com.coolbook.erp.model.WorkShiftGet;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public abstract class AbstractWorkShiftResourceAssembler extends ResourceAssemblerSupport<WorkShiftEntity, WorkShiftGet> {
    protected String requestURI;

    public AbstractWorkShiftResourceAssembler() {
        super(WorkShiftEntity.class, WorkShiftGet.class);
    }

    @Override
    public WorkShiftGet toResource(WorkShiftEntity WorkShiftEntity) {
        return createWorkShiftJson(WorkShiftEntity);
    }

    private WorkShiftGet createWorkShiftJson(WorkShiftEntity workShiftEntity) {
        WorkShiftGet workShiftGet = new WorkShiftGet();
        workShiftGet.setWork_Shift_id(workShiftEntity.getId());
        workShiftGet.setWorkShiftName(workShiftEntity.getWorkShiftName());
        workShiftGet.setTimeFrom(workShiftEntity.getTimeFrom());
        workShiftGet.setTimeTo(workShiftEntity.getTimeTo());
        workShiftGet.setHoursPerDay(workShiftEntity.getHoursPerDay());
        return workShiftGet;
    }

    protected abstract String getWorkShiftSelfLink(String id);
}
