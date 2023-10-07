package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.WorkOrderEntity;
import com.coolbook.erp.repository.WorkOrderRepository;
import com.coolbook.erp.repository.specs.WorkOrderSearchSpecification;
import com.coolbook.erp.repository.specs.WorkOrderSpecification;
import com.coolbook.erp.rest.searchCriteria.WorkOrderCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderService extends BaseService{

    @Autowired
    private WorkOrderRepository workOrderRepository;

    public long saveWorkOrder(WorkOrderEntity workOrder){
        setMetaData(workOrder,null);
        long id=this.workOrderRepository.save(workOrder).getId();
        return id;
    }

    public void updateWorkOrder(WorkOrderEntity workOrder, long id) {
        workOrder.setId(id);
        setMetaData(workOrder,this.workOrderRepository.getOne(id));
        this.workOrderRepository.save(workOrder);
    }

    public void deleteWorkOrder(long id) {

        this.workOrderRepository.delete(id);
    }

    public WorkOrderEntity getWorkOrderById(long id) {
        return this.workOrderRepository.getOne(id);
    }

    public Page<WorkOrderEntity> getAllWorkOrder(Pageable page, WorkOrderCriteria criteria) {
        WorkOrderSpecification specification = new WorkOrderSpecification(criteria);
        return this.workOrderRepository.findAll(specification, page);
    }

    public Page<WorkOrderEntity> searchWorkOrder(Pageable page, String searchValue) {
        WorkOrderSearchSpecification specification = new WorkOrderSearchSpecification(searchValue);
        return this.workOrderRepository.findAll(specification, page);
    }
}
