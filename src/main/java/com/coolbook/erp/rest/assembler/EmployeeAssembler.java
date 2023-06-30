package com.coolbook.erp.rest.assembler;

import com.coolbook.erp.common.enums.StatusEnum;
import com.coolbook.erp.entity.*;
import com.coolbook.erp.model.*;
import com.coolbook.erp.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class EmployeeAssembler {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private BranchRepository branchRepository;

    public EmployeeGet assembleEmployeeGet(EmployeeEntity employeeEntity) {
        EmployeeGet employeeGet = new EmployeeGet();
        employeeGet.setEmployee_Id(employeeEntity.getId());
        employeeGet.setNicNumber(employeeEntity.getNicNumber());
        employeeGet.setEmployeeName(employeeEntity.getEmployeeName());
        employeeGet.setAddressLine1(employeeEntity.getAddressLine1());
        employeeGet.setAddressLine2(employeeEntity.getAddressLine2());
        employeeGet.setAddressLine3(employeeEntity.getAddressLine3());
        employeeGet.setMobileNumber(employeeEntity.getMobileNumber());
        employeeGet.setHomePhone(employeeEntity.getHomePhone());
        employeeGet.setImageUrl(employeeEntity.getImageUrl());
        employeeGet.setStatus(StatusEnum.getByCode(employeeEntity.getStatus()));
        employeeGet.setBankName(employeeEntity.getBankName());
        employeeGet.setBankBranch(employeeEntity.getBankBranch());
        employeeGet.setAccountNumber(employeeEntity.getAccountNumber());
        employeeGet.setEmployeeJob(assembleEmployeeJobGet(employeeEntity.getEmployeeJob()));
        return employeeGet;
    }

    public EmployeeEntity assembleEmployeeEntity(EmployeePost employeePost) {
        EmployeeEntity employeeEntity=new EmployeeEntity();
        employeeEntity.setNicNumber(employeePost.getNicNumber());
        employeeEntity.setEmployeeName(employeePost.getEmployeeName());
        employeeEntity.setAddressLine1(employeePost.getAddressLine1());
        employeeEntity.setAddressLine2(employeePost.getAddressLine2());
        employeeEntity.setAddressLine3(employeePost.getAddressLine3());
        employeeEntity.setMobileNumber(employeePost.getMobileNumber());
        employeeEntity.setHomePhone(employeePost.getHomePhone());
        employeeEntity.setImageUrl(employeePost.getImageUrl());
        if(employeePost.getStatus()!=null)
            employeeEntity.setStatus(employeePost.getStatus().getCode());
        employeeEntity.setBankName(employeePost.getBankName());
        employeeEntity.setBankBranch(employeePost.getBankBranch());
        employeeEntity.setAccountNumber(employeePost.getAccountNumber());
        if(employeePost.getMaritalStatus()!=null)
            employeeEntity.setMaritalStatus(employeePost.getMaritalStatus());
        if(employeePost.getGender()!=null)
            employeeEntity.setGender(employeePost.getGender());
        employeeEntity.setEmployeeJob(assembleEmployeeJob(employeePost.getEmployeeJob(),employeeEntity));
        return employeeEntity;
    }

    private EmployeeJobEntity assembleEmployeeJob(EmployeeJobPost employeeJob,EmployeeEntity employeeEntity){
        EmployeeJobEntity employeeJobEntity = new EmployeeJobEntity();
        if(employeeJob!=null) {
            employeeJobEntity.setJobTitle(new JobTitlesEntity(employeeJob.getJobTitleId()));
            employeeJobEntity.setJoinedDate(employeeJob.getJoinedDate());
            employeeJobEntity.setBranchId(employeeJob.getBranchId());
            employeeJobEntity.setPaymentFrequency(employeeJob.getPaymentFrequency());
            employeeJobEntity.setPaymentRate(employeeJob.getPaymentRate());
            employeeJobEntity.setJobCategory(new JobCategoryEntity(employeeJob.getJobCategoryId()));
            employeeJobEntity.setEmploymentStatus(employeeJob.getEmploymentStatus());
            employeeJobEntity.setWorkShift(new WorkShiftEntity(employeeJob.getWorkShiftId()));
            employeeJobEntity.setProbationEndDate(employeeJob.getProbationEndDate());
            employeeJobEntity.setEmployee(employeeEntity);
        }
        return employeeJobEntity;
    }

    private EmployeeJobGet assembleEmployeeJobGet(EmployeeJobEntity employeeJob){
        EmployeeJobGet employeeJobGet = new EmployeeJobGet();
        if(employeeJob!=null) {
            employeeJobGet.setJob_title_Id(employeeJob.getId());
            employeeJobGet.setJobTitle(assembleJobTitlesGet(employeeJob.getJobTitle()));
            employeeJobGet.setJoinedDate(dateFormat.format(employeeJob.getJoinedDate()));
            employeeJobGet.setBranch(assembleBranchGet(new BranchEntity(employeeJob.getBranchId())));
            employeeJobGet.setPaymentFrequency(employeeJob.getPaymentFrequency());
            employeeJobGet.setPaymentRate(employeeJob.getPaymentRate());
            employeeJobGet.setJobCategory(assembleJobCategoryGet(employeeJob.getJobCategory()));
            employeeJobGet.setEmploymentStatus(employeeJob.getEmploymentStatus());
            employeeJobGet.setWorkShift(assembleWorkShiftGet(employeeJob.getWorkShift()));
            employeeJobGet.setProbationEndDate(dateFormat.format(employeeJob.getProbationEndDate()));

        }
        return employeeJobGet;
    }

    private JobTitlesGet assembleJobTitlesGet(JobTitlesEntity jobTitle){
        JobTitlesGet jobTitlesGet=new JobTitlesGet();
        if(jobTitle!=null) {
            jobTitlesGet.setJob_Titles_id(jobTitle.getId());
            jobTitlesGet.setJobDescription(jobTitle.getJobDescription());
        }
        return jobTitlesGet;
    }
    
    private BranchGet assembleBranchGet(BranchEntity branch){

            BranchGet branchGet = new BranchGet();
        if(branch!=null) {
            branchGet.setBranch_id(branch.getId());
            branchGet.setBranchCode(branch.getBranchCode());
            branchGet.setBranchName(branch.getBranchName());
        }
            return branchGet;

    }

    private JobCategoryGet assembleJobCategoryGet(JobCategoryEntity jobCategory){

            JobCategoryGet jobCategoryGet = new JobCategoryGet();
        if(jobCategory!=null) {
            jobCategoryGet.setCategoryName(jobCategory.getCategoryName());
            jobCategoryGet.setDescription(jobCategory.getDescription());
            jobCategoryGet.setJob_Category_id(jobCategory.getId());

        }
        return jobCategoryGet;
    }

    private WorkShiftGet assembleWorkShiftGet(WorkShiftEntity workShift){

            WorkShiftGet workShiftGet = new WorkShiftGet();
        if(workShift!=null) {
            workShiftGet.setWork_Shift_id(workShift.getId());
            workShiftGet.setWorkShiftName(workShift.getWorkShiftName());
            workShiftGet.setTimeFrom(workShift.getTimeFrom());
            workShiftGet.setTimeTo(workShift.getTimeTo());
            workShiftGet.setHoursPerDay(workShift.getHoursPerDay());

        }
        return workShiftGet;
    }
}
