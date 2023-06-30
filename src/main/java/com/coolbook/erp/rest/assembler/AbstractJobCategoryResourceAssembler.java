package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.JobCategoryEntity;
import com.coolbook.erp.model.JobCategoryGet;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public abstract class AbstractJobCategoryResourceAssembler extends ResourceAssemblerSupport<JobCategoryEntity, JobCategoryGet> {
    protected String requestURI;

    public AbstractJobCategoryResourceAssembler() {
        super(JobCategoryEntity.class, JobCategoryGet.class);
    }

    @Override
    public JobCategoryGet toResource(JobCategoryEntity JobCategoryEntity) {
        return createJobCategoryJson(JobCategoryEntity);
    }

    private JobCategoryGet createJobCategoryJson(JobCategoryEntity JobCategoryEntity) {
        JobCategoryGet JobCategoryGet = new JobCategoryGet();
        JobCategoryGet.setJob_Category_id(JobCategoryEntity.getId());
        JobCategoryGet.setCategoryName(JobCategoryEntity.getCategoryName());
        JobCategoryGet.setDescription(JobCategoryEntity.getDescription());
        return JobCategoryGet;
    }

    protected abstract String getJobCategorySelfLink(String id);
}

