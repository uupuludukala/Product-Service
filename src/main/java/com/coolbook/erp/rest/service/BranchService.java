package com.coolbook.erp.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coolbook.erp.entity.BranchEntity;
import com.coolbook.erp.repository.BranchRepository;
import com.coolbook.erp.repository.specs.BranchSearchSpecification;
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
	
	public void updateBranch(BranchEntity branch, long id) {
		branch.setId(id);
		this.branchRepository.save(branch);
	}

	public void deleteBranch(long id) {

    this.branchRepository.delete(id);

	}
	public BranchEntity getBranchById(long id) {
		return this.branchRepository.getOne(id);
		
	}
	public Page<BranchEntity> getAllBranch(Pageable page,BranchCriteria searchCriteria){
		BranchSpecification specification=new BranchSpecification(searchCriteria);
		return this.branchRepository.findAll(specification,page);
	}
	
	
	public Page<BranchEntity> searchBranchByCompany(Pageable page, String searchValue,long companyId) {
		BranchSearchSpecification specification = new BranchSearchSpecification(searchValue,companyId);
		return this.branchRepository.findAll(specification, page);
	}
	
	public Page<BranchEntity> searchBranch(Pageable page, String searchValue) {
		BranchSearchSpecification specification = new BranchSearchSpecification(searchValue,0);
		return this.branchRepository.findAll(specification, page);
	}
	
}
