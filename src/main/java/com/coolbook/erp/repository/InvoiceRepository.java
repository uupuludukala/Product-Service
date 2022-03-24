package com.coolbook.erp.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.coolbook.erp.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface InvoiceRepository  extends CrudRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {


}
