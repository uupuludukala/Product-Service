package com.coolbook.erp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product_category")
public class ProductCategoryEntity {
	@Id
	@Column
	@GeneratedValue(generator = "product_category_seq")
	@SequenceGenerator(name = "product_category_seq", sequenceName = "product_category_seq", allocationSize = 1)
	private long id;
	@Column
	private String productcatCode;
	@Column
	private long parentCategory;
	@Column
	private String productcatName;

}
