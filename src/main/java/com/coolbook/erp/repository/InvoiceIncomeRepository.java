package com.coolbook.erp.repository;

import com.coolbook.erp.entity.InvoiceEntity;
import com.coolbook.erp.entity.InvoiceIncomeEntity;
import com.coolbook.erp.entity.InvoiceInventoryDetailsEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceIncomeRepository  extends CrudRepository<InvoiceIncomeEntity, Long>, JpaSpecificationExecutor<InvoiceIncomeEntity> {

    @Query(value="from InvoiceIncomeEntity i where i.invoice =?1 ")
    List<InvoiceIncomeEntity> getInvoiceIncomesByInvoice(InvoiceEntity invoice);
    
    
}
