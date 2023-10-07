package com.coolbook.erp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "mesure_unit")
public class MesureUnitEntity extends BaseEntity{
	@Id
	@Column
	@GeneratedValue(generator = "mesure_unit_seq")
	@SequenceGenerator(name = "mesure_unit_seq", sequenceName = "mesure_unit_seq", allocationSize = 1)
	private long id;
	
	@Column
	private String code;
	
	@Column
	private String description;
}
