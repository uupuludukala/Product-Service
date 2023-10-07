package com.coolbook.erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "workOrderComponent")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class WorkOrderComponentEntity extends BaseEntity{

    @Id
    @Column
    @GeneratedValue(generator = "work_order_component_seq")
    @SequenceGenerator(name = "work_order_component_seq", sequenceName = "work_order_component_seq", allocationSize = 1)
    private long id;

    @OneToOne
    private ProductEntity product;

    @Column
    private double quantity;

    @Column
    private double cost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "work_order_id", nullable = false)
    private WorkOrderEntity workOrder;
}
