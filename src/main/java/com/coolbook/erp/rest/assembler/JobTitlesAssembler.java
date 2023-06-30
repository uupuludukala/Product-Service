package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.entity.JobTitlesEntity;
import com.coolbook.erp.model.JobTitlesGet;
import com.coolbook.erp.model.JobTitlesPost;
import com.coolbook.erp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobTitlesAssembler {


    CompanyRepository companyRepository;

    @Autowired
    JobTitlesAssembler(CompanyRepository companyRepository){
        this.companyRepository=companyRepository;
    }



    public JobTitlesEntity essembleJobTitlesEntity(JobTitlesPost JobTitlesPost) {
        JobTitlesEntity  jobTitlesEntity=new JobTitlesEntity ();
        jobTitlesEntity.setJobTitle(JobTitlesPost.getJobTitle());
        jobTitlesEntity.setJobDescription(JobTitlesPost.getJobDescription());
        jobTitlesEntity.setNote(JobTitlesPost.getNote());
        return jobTitlesEntity;
    }

    public JobTitlesGet essembleJobTitlesGet(JobTitlesEntity JobTitlesEntity) {
        JobTitlesGet jobTitlesGet=new JobTitlesGet();
        jobTitlesGet.setJob_Titles_id(JobTitlesEntity.getId());
        jobTitlesGet.setJobTitle(JobTitlesEntity.getJobTitle());
        jobTitlesGet.setJobDescription(JobTitlesEntity.getJobDescription());
        jobTitlesGet.setNote(JobTitlesEntity.getNote());
        return jobTitlesGet;
    }
}
