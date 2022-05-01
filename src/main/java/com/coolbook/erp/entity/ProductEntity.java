package com.coolbook.erp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ProductEntity {
	public ProductEntity(long id) {
		this.id = id;
	}

	public ProductEntity() {

	}

	@Id
	@Column
	@GeneratedValue(generator = "product_seq")
	@SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
	private long id;

	@Column
	private boolean canBeSold;

	@Column
	private boolean canBePurchased;
	
	@Column
	private String productName;
	
	@Column
	private String productType;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "productCat_id", referencedColumnName = "id")
	private ProductCategoryEntity productCategory;
	
	@Column(unique = true)
	private String barcode;

	@Column(unique = true)
	private String productCode;

	@Column
	private double salePrice;
	
	@Column
	private double cost;
	
	@Column
	private boolean active;

	@Column
	private double quantity;

	@OneToOne
	private MesureUnitEntity unit;

	@Column
	private String imageUrl;

	@Column
	private boolean availableInPos;

	@Column
	private boolean makeToOrder;

	@Column
	private double customerLeadTime;

	@Column
	private String descDelOrder;

	@Column
	private String descReceipt;

	@Column
	private double weight;

	@Column
	private double volume;

	@Column
	private long responsible;

    @Column
    private String status;
	
	@ManyToMany
    @JoinTable(
            name = "product_company",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "company_id")}
    )
	private Set<CompanyEntity> companies;
	
	@Column
	private int warrantyMonths;

	@Column
    private int warrantyYears;

}
