package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.JobCategoryEntity;
import com.coolbook.erp.repository.JobCategoryRepository;
import com.coolbook.erp.repository.specs.JobCategorySearchSpecification;
import com.coolbook.erp.repository.specs.JobCategorySpecification;
import com.coolbook.erp.rest.searchCriteria.JobCategoryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JobCategoryService {
    JobCategoryRepository jobCategoryRepository;

    @Autowired
    JobCategoryService(JobCategoryRepository jobCategoryRepository){
        this.jobCategoryRepository=jobCategoryRepository;
    }
    public long saveJobCategory(JobCategoryEntity jobCategory) {
        return this.jobCategoryRepository.save(jobCategory).getId();
    }

    public void updateJobCategory(JobCategoryEntity jobCategory, long id) {
        jobCategory.setId(id);
        this.jobCategoryRepository.save(jobCategory);
    }

    public void deleteJobCategory(long id) {

        this.jobCategoryRepository.delete(id);

    }
    public JobCategoryEntity getJobCategoryById(long id) {
        return this.jobCategoryRepository.getOne(id);

    }
    public Page<JobCategoryEntity> getAllJobCategory(Pageable page, JobCategoryCriteria searchCriteria){
        JobCategorySpecification specification=new JobCategorySpecification(searchCriteria);
        return this.jobCategoryRepository.findAll(specification,page);
    }




    public Page<JobCategoryEntity> searchJobCategory(Pageable page, String searchValue) {
        JobCategorySearchSpecification specification = new JobCategorySearchSpecification(searchValue);
        return this.jobCategoryRepository.findAll(specification, page);
    }

}
