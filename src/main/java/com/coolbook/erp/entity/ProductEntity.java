package com.coolbook.erp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private String productCode;
	@Column
	private String productName;
	@Column
	private String productType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "productCat_id", referencedColumnName = "id")
	private ProductCategoryEntity productCategory;
	@Column
	private String barcode;
	@Column
	private double salePrice;
	@Column
	private double cost;
	@Column
	private boolean active;
	@Column
	private int quantity;

	@OneToOne
	private MesureUnitEntity unit;

	@Column
	private String imageUrl;

}
