package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.JobCategoryEntity;
import com.coolbook.erp.model.JobCategoryGet;
import com.coolbook.erp.model.JobCategoryPost;
import com.coolbook.erp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobCategoryAssembler {

    CompanyRepository companyRepository;

    @Autowired
    JobCategoryAssembler(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public JobCategoryEntity essembleJobCategoryEntity(JobCategoryPost JobCategoryPost) {
        JobCategoryEntity jobCategoryEntity = new JobCategoryEntity();
        jobCategoryEntity.setCategoryName(JobCategoryPost.getCategoryName());
        jobCategoryEntity.setDescription(JobCategoryPost.getDescription());
        return jobCategoryEntity;
    }

    public JobCategoryGet essembleJobCategoryGet(JobCategoryEntity JobCategoryEntity) {
        JobCategoryGet jobCategoryGet = new JobCategoryGet();
        jobCategoryGet.setJob_Category_id(JobCategoryEntity.getId());
        jobCategoryGet.setCategoryName(JobCategoryEntity.getCategoryName());
        jobCategoryGet.setDescription(JobCategoryEntity.getDescription());
        return jobCategoryGet;
    }
}
