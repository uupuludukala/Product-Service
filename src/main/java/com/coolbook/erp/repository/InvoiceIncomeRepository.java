package com.coolbook.erp.repository;

import com.coolbook.erp.entity.InvoiceEntity;
import com.coolbook.erp.entity.InvoiceIncomeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceIncomeRepository  extends CrudRepository<InvoiceIncomeEntity, Long>, JpaSpecificationExecutor<InvoiceIncomeEntity> {
}
