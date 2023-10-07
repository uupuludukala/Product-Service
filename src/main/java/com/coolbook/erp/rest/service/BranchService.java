package com.coolbook.erp.rest.service;

import com.coolbook.erp.dataSourceRouting.DataSourceContextHolder;
import com.coolbook.erp.entity.BranchEntity;
import com.coolbook.erp.repository.BranchRepository;
import com.coolbook.erp.repository.specs.BranchSearchSpecification;
import com.coolbook.erp.repository.specs.BranchSpecification;
import com.coolbook.erp.rest.searchCriteria.BranchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BranchService extends BaseService{
	BranchRepository branchRepository;

	private DataSourceContextHolder dataSourceContextHolder;
	@Autowired
	BranchService(BranchRepository branchRepository,DataSourceContextHolder dataSourceContextHolder){
		this.branchRepository=branchRepository;
		this.dataSourceContextHolder = dataSourceContextHolder;
	}
	public long saveBranch(BranchEntity branch) {
		setMetaData(branch,null);
		return this.branchRepository.save(branch).getId();
	}

	public void updateBranch(BranchEntity branch, long id) {
		branch.setId(id);
		setMetaData(branch,this.branchRepository.getOne(id));
		this.branchRepository.save(branch);
	}

	public void deleteBranch(long id) {

    this.branchRepository.delete(id);

	}
	public BranchEntity getBranchById(long id) {
		dataSourceContextHolder.setDataSourceContext("coop");
		return this.branchRepository.getOne(id);
		
	}
	public Page<BranchEntity> getAllBranch(Pageable page,BranchCriteria searchCriteria){
		BranchSpecification specification=new BranchSpecification(searchCriteria,securityFacade.getCurrentUser().getCompanyCode());
		return this.branchRepository.findAll(specification,page);
	}
	
	
	public Page<BranchEntity> searchBranchByCompany(Pageable page, String searchValue,long companyId) {
		BranchSearchSpecification specification = new BranchSearchSpecification(searchValue,companyId,securityFacade.getCurrentUser().getCompanyCode());
		return this.branchRepository.findAll(specification, page);
	}
	
	public Page<BranchEntity> searchBranch(Pageable page, String searchValue) {
		BranchSearchSpecification specification = new BranchSearchSpecification(searchValue,0,securityFacade.getCurrentUser().getCompanyCode());
		return this.branchRepository.findAll(specification, page);
	}
	
}
