package com.coolbook.erp.repository;

import com.coolbook.erp.entity.InvoiceEntity;
import com.coolbook.erp.entity.InvoiceProductEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceProductRepository extends CrudRepository<InvoiceProductEntity, Long>, JpaSpecificationExecutor<InvoiceProductEntity> {
}
