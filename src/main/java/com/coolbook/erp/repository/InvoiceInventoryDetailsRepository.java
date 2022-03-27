package com.coolbook.erp.repository;

import com.coolbook.erp.entity.InvoiceInventoryDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceInventoryDetailsRepository extends JpaRepository<InvoiceInventoryDetailsEntity, Long>, JpaSpecificationExecutor<InvoiceInventoryDetailsEntity> {

    @Query(value="from InvoiceInventoryDetailsEntity i where i.invoiceId =?1 ")
    List<InvoiceInventoryDetailsEntity> getInvoiceInventoryDetails(long invoiceId);
}
