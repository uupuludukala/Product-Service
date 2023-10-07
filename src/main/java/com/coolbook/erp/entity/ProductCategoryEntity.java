package com.coolbook.erp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "product_category")
public class ProductCategoryEntity extends BaseEntity{
	@Id
	@Column
	@GeneratedValue(generator = "product_category_seq")
	@SequenceGenerator(name = "product_category_seq", sequenceName = "product_category_seq", allocationSize = 1)
	private long id;
	
	@Column(unique = true)
	private String productCategoryCode;
	
	@Column
	private long parentCategory;
	
	@Column
	private String productCategoryName;

    @Column
    private String status;
	
	@OneToMany(cascade= {CascadeType.MERGE},fetch=FetchType.LAZY,mappedBy="productCategory")
	private List<ProductEntity> product;

}
