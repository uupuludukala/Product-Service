package com.coolbook.erp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "branch", uniqueConstraints = @UniqueConstraint(columnNames = {"company_id", "branchName", "branchCode"}))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BranchEntity  extends BaseEntity{

    @Id
    @Column
    @GeneratedValue(generator = "branch_seq")
    @SequenceGenerator(name = "branch_seq", sequenceName = "branch_seq", allocationSize = 1)
    private long id;
    @Column
    private String branchCode;
    @Column
    private String branchName;
    @Column
    private String addressLine1;
    @Column
    private String addressLine2;
    @Column
    private String addressLine3;
    @Column
    private String contactNumber;
    @Column
    private String status;
    @OneToOne
    @ApiParam(hidden = true)
    private CompanyEntity company;

    public BranchEntity(long id) {
        this.id = id;
    }

    public BranchEntity() {

    }


}
