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
@Table(name = "mesure_unit")
public class MesureUnitEntity {
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
