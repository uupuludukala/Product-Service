package com.coolbook.erp.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.BranchEntity;
import com.coolbook.erp.repository.BranchRepository;
import com.coolbook.erp.repository.specs.BranchSpecification;
import com.coolbook.erp.rest.searchCriteria.BranchCriteria;

@Service
public class BranchService {
BranchRepository branchRepository;
	
	@Autowired
	BranchService(BranchRepository branchRepository){
		this.branchRepository=branchRepository;
	}
	public long saveBranch(BranchEntity branch) {
		return this.branchRepository.save(branch).getId();
	}
	public BranchEntity getBranchById(long id) {
		return this.branchRepository.getOne(id);
		
	}
	public Page<BranchEntity> getAllBranch(Pageable page,BranchCriteria searchCriteria){
		BranchSpecification specification=new BranchSpecification(searchCriteria);
		return this.branchRepository.findAll(specification,page);
	}
}
