package com.coolbook.erp.repository;


import com.coolbook.erp.entity.InvoiceProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProductEntity, Long>, JpaSpecificationExecutor<InvoiceProductEntity> {
}
