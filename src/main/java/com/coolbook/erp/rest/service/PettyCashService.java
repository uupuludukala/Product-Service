package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.PettyCashEntity;
import com.coolbook.erp.repository.PettyCashRepository;
import com.coolbook.erp.repository.specs.PettyCashSearchSpecification;
import com.coolbook.erp.repository.specs.PettyCashSpecification;
import com.coolbook.erp.rest.searchCriteria.PettyCashCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PettyCashService {
    PettyCashRepository pettyCashRepository;

    @Autowired
    PettyCashService(PettyCashRepository pettyCashRepository){
        this.pettyCashRepository=pettyCashRepository;
    }
    public long savePettyCash(PettyCashEntity pettyCash) {
        return this.pettyCashRepository.save(pettyCash).getId();
    }

    public void updatePettyCash(PettyCashEntity pettyCash, long id) {
        pettyCash.setId(id);
        this.pettyCashRepository.save(pettyCash);
    }

    public void deletePettyCash(long id) {

        this.pettyCashRepository.delete(id);

    }
    public PettyCashEntity getPettyCashById(long id) {
        return this.pettyCashRepository.getOne(id);

    }
    public Page<PettyCashEntity> getAllPettyCash(Pageable page, PettyCashCriteria searchCriteria){
        PettyCashSpecification specification=new PettyCashSpecification(searchCriteria);
        return this.pettyCashRepository.findAll(specification,page);
    }


    public Page<PettyCashEntity> searchPettyCashByCompany(Pageable page, String searchValue,long companyId) {
        PettyCashSearchSpecification specification = new PettyCashSearchSpecification(searchValue,companyId);
        return this.pettyCashRepository.findAll(specification, page);
    }

    public Page<PettyCashEntity> searchPettyCash(Pageable page, String searchValue) {
        PettyCashSearchSpecification specification = new PettyCashSearchSpecification(searchValue,0);
        return this.pettyCashRepository.findAll(specification, page);
    }

}
