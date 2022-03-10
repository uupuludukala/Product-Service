package com.coolbook.erp.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vendor_account")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class VendorAccountEntity {

    @Id
    @Column
    @GeneratedValue(generator="vendor_account_seq")
    @SequenceGenerator(name="vendor_account_seq",sequenceName="vendor_account_seq" ,allocationSize=1)
    private long id;
 
    @Column
    private String accountNumber;

    @Column
    private String bankName;

    @Column
    private String branchName;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "vendor_id", nullable = false)
    private VendorEntity vendor;
    
}
