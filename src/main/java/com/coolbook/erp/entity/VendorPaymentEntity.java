package com.coolbook.erp.entity;

import com.coolbook.erp.common.enums.PaymentStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vendorPayment")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class VendorPaymentEntity extends BaseEntity{
    @Id
    @Column
    @GeneratedValue(generator="vendor_payment_seq")
    @SequenceGenerator(name="vendor_payment_seq",sequenceName="vendor_payment_seq" ,allocationSize=1)
    private long id;

    @OneToOne
    private VendorEntity vendor;

    @Column
    private Date date;

    @Column
    private String bankName;

    @Column
    private String bankBranch;

    @Column
    private String accountNumber;

    @Column
    private double amount;

    @Column
    private String remarks;

    @Column
    private PaymentStatusEnum status;
    
}
