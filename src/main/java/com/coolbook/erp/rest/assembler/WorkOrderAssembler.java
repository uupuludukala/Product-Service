package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.*;
import com.coolbook.erp.model.*;
import com.coolbook.erp.rest.service.EmployeeService;
import com.coolbook.erp.rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class WorkOrderAssembler {

    DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProductService productService;
    
    public WorkOrderGet assembleWorkOrderGet(WorkOrderEntity workOrderEntity) {
        WorkOrderGet workOrderGet = new WorkOrderGet();

        workOrderGet.setWork_order_Id(workOrderEntity.getId());
        workOrderGet.setWorkOrderNumber(workOrderEntity.getWorkOrderNumber());
        workOrderGet.setWorkOrderDescription(workOrderEntity.getWorkOrderDescription());
        workOrderGet.setTechnician(new EmployeeGet(workOrderEntity.getTechnician().getId(),workOrderEntity.getTechnician().getEmployeeName()));
        workOrderGet.setSupervisor(new EmployeeGet(workOrderEntity.getSupervisor().getId(),workOrderEntity.getSupervisor().getEmployeeName()));
        workOrderGet.setStartDate(dateFormat2.format(workOrderEntity.getStartDate()));
        workOrderGet.setHelper(new EmployeeGet(workOrderEntity.getHelper().getId(),workOrderEntity.getHelper().getEmployeeName()));
        workOrderGet.setTargetFinishDate(dateFormat2.format(workOrderEntity.getTargetFinishDate()));
        workOrderGet.setEndDate(dateFormat2.format(workOrderEntity.getEndDate()));
        workOrderGet.setComponents(assembleWorkOrderComponentGet(workOrderEntity.getComponents()));
        workOrderGet.setLabours(assembleWorkOrderLabourGet(workOrderEntity.getLabours()));
        workOrderGet.setTotalCost(workOrderEntity.getTotalCost());
        return workOrderGet;
    }

    public List<WorkOrderLabourGet> assembleWorkOrderLabourGet(List<WorkOrderLabourEntity> workOrderLabours){
        List<WorkOrderLabourGet> labours= new ArrayList<>();
        for(WorkOrderLabourEntity labour:workOrderLabours){
            WorkOrderLabourGet workOrderLabourGet=new WorkOrderLabourGet();
            workOrderLabourGet.setEmployee(new EmployeeGet(labour.getEmployee().getId(),labour.getEmployee().getEmployeeName()));
            workOrderLabourGet.setRate(labour.getRate());
            workOrderLabourGet.setHours(labour.getHours());
            labours.add(workOrderLabourGet);
        }
        return labours;
    }

    public List<WorkOrderComponentGet> assembleWorkOrderComponentGet(List<WorkOrderComponentEntity> workOrderComponents){
        List<WorkOrderComponentGet> components= new ArrayList<>();
        for(WorkOrderComponentEntity workOrderComponent:workOrderComponents){
            WorkOrderComponentGet component=new WorkOrderComponentGet();
            component.setProduct(new ProductGet(workOrderComponent.getProduct().getId(),workOrderComponent.getProduct().getProductCode()
                    ,workOrderComponent.getProduct().getProductName()));
            component.setQuantity(workOrderComponent.getQuantity());
            component.setCost(workOrderComponent.getCost());
            components.add(component);
           }
        return components;
    }

    public WorkOrderEntity essembleWorkOrderEntity(WorkOrderPost workOrderPost) {
        WorkOrderEntity workOrderEntity=new WorkOrderEntity();
        workOrderEntity.setWorkOrderNumber(workOrderPost.getWorkOrderNumber());
        workOrderEntity.setWorkOrderDescription(workOrderPost.getWorkOrderDescription());
        workOrderEntity.setHelper(employeeService.getEmployeeById(workOrderPost.getHelper()));
        workOrderEntity.setSupervisor(employeeService.getEmployeeById(workOrderPost.getSupervisor()));
        workOrderEntity.setTechnician(employeeService.getEmployeeById(workOrderPost.getTechnician()));
        workOrderEntity.setStartDate(workOrderPost.getStartDate());
        workOrderEntity.setEndDate(workOrderPost.getEndDate());
        workOrderEntity.setTargetFinishDate(workOrderPost.getTargetFinishDate());
        workOrderEntity.setComponents(assembleWorkOrderComponentEntity(workOrderPost.getComponents(),workOrderEntity));
        workOrderEntity.setLabours(assembleWorkOrderLabourEntity(workOrderPost.getLabours(),workOrderEntity));
        return workOrderEntity;
    }

    public List<WorkOrderComponentEntity> assembleWorkOrderComponentEntity(List<WorkOrderComponent> workOrderComponents, WorkOrderEntity workOrderEntity) {
        List<WorkOrderComponentEntity> workOrderComponentEntities=new ArrayList<>();
        if(workOrderComponents!=null) {
            for (WorkOrderComponent workOrderComponent : workOrderComponents) {
                ProductEntity productEntity=productService.getProductById(workOrderComponent.getProductId());
                WorkOrderComponentEntity workOrderComponentEntity = new WorkOrderComponentEntity();
                workOrderComponentEntity.setProduct(productEntity);
                workOrderComponentEntity.setQuantity(workOrderComponent.getQuantity());
                workOrderComponentEntity.setWorkOrder(workOrderEntity);
                workOrderComponentEntity.setCost(productEntity.getCost());
                workOrderEntity.setTotalCost(workOrderEntity.getTotalCost()+(workOrderComponent.getQuantity()*productEntity.getCost()));
                workOrderComponentEntities.add(workOrderComponentEntity);
            }
        }
        return workOrderComponentEntities;
    }

    public List<WorkOrderLabourEntity> assembleWorkOrderLabourEntity(List<WorkOrderLabour> workOrderLabours, WorkOrderEntity workOrderEntity) {
        List<WorkOrderLabourEntity> workOrderLabourEntities=new ArrayList<>();
        if(workOrderLabours!=null) {
            for (WorkOrderLabour workOrderLabour : workOrderLabours) {
                WorkOrderLabourEntity workOrderLabourEntity = new WorkOrderLabourEntity();
                workOrderLabourEntity.setEmployee(new EmployeeEntity(workOrderLabour.getEmployeeId()));
                workOrderLabourEntity.setHours(workOrderLabour.getHours());
                workOrderLabourEntity.setRate(workOrderLabour.getRate());
                workOrderLabourEntity.setWorkOrder(workOrderEntity);
                workOrderEntity.setTotalCost(workOrderEntity.getTotalCost()+(workOrderLabour.getHours()*workOrderLabour.getRate()));
                workOrderLabourEntities.add(workOrderLabourEntity);
            }
        }
        return workOrderLabourEntities;
    }
}
