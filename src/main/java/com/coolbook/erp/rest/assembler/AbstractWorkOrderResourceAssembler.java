package com.coolbook.erp.rest.assembler;


import com.coolbook.erp.entity.WorkOrderComponentEntity;
import com.coolbook.erp.entity.WorkOrderEntity;
import com.coolbook.erp.entity.WorkOrderLabourEntity;
import com.coolbook.erp.model.*;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class AbstractWorkOrderResourceAssembler extends ResourceAssemblerSupport<WorkOrderEntity, WorkOrderGet> {
    protected String requestURI;

    DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");

    public AbstractWorkOrderResourceAssembler() {
        super(WorkOrderEntity.class, WorkOrderGet.class);
    }

    @Override
    public WorkOrderGet toResource(WorkOrderEntity workOrderEntity) {
        return createWorkOrderJson(workOrderEntity);
    }

    private WorkOrderGet createWorkOrderJson(WorkOrderEntity workOrderEntity) {
        WorkOrderGet workOrderGet = new WorkOrderGet();

        workOrderGet.setWork_order_Id(workOrderEntity.getId());
        workOrderGet.setWorkOrderNumber(workOrderEntity.getWorkOrderNumber());
        workOrderGet.setWorkOrderDescription(workOrderEntity.getWorkOrderDescription());
        workOrderGet.setHelper(new EmployeeGet(workOrderEntity.getHelper().getId(),
                workOrderEntity.getHelper().getEmployeeName()));
        workOrderGet.setSupervisor(new EmployeeGet(workOrderEntity.getSupervisor().getId(),
                workOrderEntity.getSupervisor().getEmployeeName()));
        workOrderGet.setTechnician(new EmployeeGet(workOrderEntity.getTechnician().getId(),
                workOrderEntity.getTechnician().getEmployeeName()));
        workOrderGet.setEndDate(workOrderEntity.getEndDate()!=null ?dateFormat2.format(workOrderEntity.getEndDate()):null);
        workOrderGet.setStartDate(workOrderEntity.getStartDate()!=null ?dateFormat2.format(workOrderEntity.getStartDate()):null);
        workOrderGet.setTargetFinishDate(workOrderEntity.getTargetFinishDate()!=null ?dateFormat2.format(workOrderEntity.getTargetFinishDate()):null);
        workOrderGet.setComponents(createComponentsJson(workOrderEntity.getComponents()));
        workOrderGet.setLabours(createLaboursJson(workOrderEntity.getLabours()));
        workOrderGet.setTotalCost(workOrderEntity.getTotalCost());
        return workOrderGet;
    }

    private List<WorkOrderLabourGet> createLaboursJson(List<WorkOrderLabourEntity> workOrderLabours){
    List<WorkOrderLabourGet> labours=new ArrayList<>();
    for(WorkOrderLabourEntity labour:workOrderLabours){
        WorkOrderLabourGet workOrderLabourGet=new WorkOrderLabourGet();
        workOrderLabourGet.setEmployee(new EmployeeGet(labour.getEmployee().getId(),labour.getEmployee()
                .getEmployeeName()));
        workOrderLabourGet.setHours(labour.getHours());
        workOrderLabourGet.setRate(labour.getRate());
        labours.add(workOrderLabourGet);
    }
    return labours;
    }

    private List<WorkOrderComponentGet> createComponentsJson(List<WorkOrderComponentEntity> workOrderComponents) {
        List<WorkOrderComponentGet> components=new LinkedList<>();
        for(WorkOrderComponentEntity worOrderComponent:workOrderComponents){
            WorkOrderComponentGet component=new WorkOrderComponentGet();
            component.setProduct(new ProductGet(worOrderComponent.getProduct().getId(),
                    worOrderComponent.getProduct().getProductCode(),worOrderComponent.getProduct().getProductName()));
            component.setQuantity(worOrderComponent.getQuantity());
            component.setCost(worOrderComponent.getCost());
            components.add(component);
        }
        return components;
    }


    protected abstract String getSelfLink(String id);
}
