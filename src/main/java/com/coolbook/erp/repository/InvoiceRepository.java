package com.coolbook.erp.repository;

import com.coolbook.erp.entity.CompanyEntity;
import com.coolbook.erp.entity.InvoiceIncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.coolbook.erp.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Transactional
public interface InvoiceRepository  extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    @Query(value="from InvoiceEntity i where i.date between ?1 and ?2 order by date")
    List<InvoiceEntity> getInvoiceByPeriod(Date dateFrom, Date dateUntil);

}
