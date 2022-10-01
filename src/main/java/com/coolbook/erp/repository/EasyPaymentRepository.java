package com.coolbook.erp.repository;

import com.coolbook.erp.entity.EasyPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EasyPaymentRepository extends JpaRepository<EasyPaymentEntity, Long>, JpaSpecificationExecutor<EasyPaymentEntity> {

}
