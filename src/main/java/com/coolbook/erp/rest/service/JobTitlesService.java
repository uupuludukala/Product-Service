package com.coolbook.erp.rest.service;

import com.coolbook.erp.entity.JobTitlesEntity;
import com.coolbook.erp.repository.JobTitlesRepository;
import com.coolbook.erp.repository.specs.JobTitlesSearchSpecification;
import com.coolbook.erp.repository.specs.JobTitlesSpecification;
import com.coolbook.erp.rest.searchCriteria.JobTitlesCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JobTitlesService extends BaseService{
    JobTitlesRepository jobTitlesRepository;

    @Autowired
    JobTitlesService(JobTitlesRepository jobTitlesRepository){
        this.jobTitlesRepository=jobTitlesRepository;
    }
    public long saveJobTitles(JobTitlesEntity jobTitles) {
        setMetaData(jobTitles,null);
        return this.jobTitlesRepository.save(jobTitles).getId();
    }

    public void updateJobTitles(JobTitlesEntity jobTitles, long id) {
        jobTitles.setId(id);
        setMetaData(jobTitles,this.jobTitlesRepository.getOne(id));
        this.jobTitlesRepository.save(jobTitles);
    }

    public void deleteJobTitles(long id) {

        this.jobTitlesRepository.delete(id);

    }
    public JobTitlesEntity getJobTitlesById(long id) {
        return this.jobTitlesRepository.getOne(id);

    }
    public Page<JobTitlesEntity> getAllJobTitles(Pageable page, JobTitlesCriteria searchCriteria){
        JobTitlesSpecification specification=new JobTitlesSpecification(searchCriteria);
        return this.jobTitlesRepository.findAll(specification,page);
    }




    public Page<JobTitlesEntity> searchJobTitles(Pageable page, String searchValue) {
        JobTitlesSearchSpecification specification = new JobTitlesSearchSpecification(searchValue);
        return this.jobTitlesRepository.findAll(specification, page);
    }

}
