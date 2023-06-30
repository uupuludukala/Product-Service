package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.JobTitlesEntity;
import com.coolbook.erp.model.JobTitlesGet;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public abstract class AbstractJobTitlesResourceAssembler extends ResourceAssemblerSupport<JobTitlesEntity, JobTitlesGet> {
    protected String requestURI;

    public AbstractJobTitlesResourceAssembler() {
        super(JobTitlesEntity.class, JobTitlesGet.class);
    }

    @Override
    public JobTitlesGet toResource(JobTitlesEntity JobTitlesEntity) {
        return createJobTitlesJson(JobTitlesEntity);
    }

    private JobTitlesGet createJobTitlesJson(JobTitlesEntity JobTitlesEntity) {
        JobTitlesGet JobTitlesGet = new JobTitlesGet();
        JobTitlesGet.setJob_Titles_id(JobTitlesEntity.getId());
        JobTitlesGet.setJobTitle(JobTitlesEntity.getJobTitle());
        JobTitlesGet.setJobDescription(JobTitlesEntity.getJobDescription());
        JobTitlesGet.setNote(JobTitlesEntity.getNote());
        return JobTitlesGet;
    }

    protected abstract String getJobTitlesSelfLink(String id);
}
