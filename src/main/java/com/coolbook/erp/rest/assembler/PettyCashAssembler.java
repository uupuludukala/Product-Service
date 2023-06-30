package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.PettyCashEntity;
import com.coolbook.erp.model.PettyCashGet;
import com.coolbook.erp.model.PettyCashPost;
import com.coolbook.erp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PettyCashAssembler {


    CompanyRepository companyRepository;

    @Autowired
    PettyCashAssembler(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public PettyCashEntity essemblePettyCashEntity(PettyCashPost pettyCashPost) {
        PettyCashEntity pettyCashEntity = new PettyCashEntity();
        pettyCashEntity.setDescription(pettyCashPost.getDescription());
        pettyCashEntity.setDate(pettyCashPost.getDate());
        pettyCashEntity.setAmount(pettyCashPost.getAmount());
        return pettyCashEntity;
    }

    public PettyCashGet essemblePettyCashGet(PettyCashEntity pettyCashEntity) {
        PettyCashGet pettyCashGet = new PettyCashGet();
        pettyCashGet.setPettyCash_id(pettyCashEntity.getId());
        pettyCashGet.setDescription(pettyCashEntity.getDescription());
        pettyCashGet.setDate(pettyCashEntity.getDate());
        pettyCashGet.setAmount(pettyCashEntity.getAmount());
        return pettyCashGet;
    }
}
