package com.coolbook.erp.entity;


import com.coolbook.erp.common.enums.VendorTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "vendor")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class VendorEntity extends BaseEntity{

    @Id
    @Column
    @GeneratedValue(generator="vendor_seq")
    @SequenceGenerator(name="vendor_seq",sequenceName="vendor_seq" ,allocationSize=1)
    private long id;

    @Column
    private VendorTypeEnum vendorType;

    @Column
    private String vendorName;

    @Column
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String addressLine3;
    
    @Column
    private String contactPerson;

    @Column
    private String phone;

    @Column
    private String mobile;

    @Column
    private String email;

    @OneToMany(mappedBy = "vendor", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL ,orphanRemoval = true)
    private Set<VendorAccountEntity> vendorAccounts;
    
    
    
}
