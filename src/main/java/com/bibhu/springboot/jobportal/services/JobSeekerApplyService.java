package com.bibhu.springboot.jobportal.services;

import com.bibhu.springboot.jobportal.entity.JobPostActivity;
import com.bibhu.springboot.jobportal.entity.JobSeekerApply;
import com.bibhu.springboot.jobportal.entity.JobSeekerProfile;
import com.bibhu.springboot.jobportal.repository.JobSeekerApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerApplyService {

    private final JobSeekerApplyRepository jobSeekerApplyRepository;

    @Autowired
    public JobSeekerApplyService(JobSeekerApplyRepository jobSeekerApplyRepository) {
        this.jobSeekerApplyRepository = jobSeekerApplyRepository;
    }

    public List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile userAccountId) {
        return jobSeekerApplyRepository.findByUserId(userAccountId);
    }

    public List<JobSeekerApply> getJobCandidates(JobPostActivity job) {
        return jobSeekerApplyRepository.findByJob(job);
    }

    public JobSeekerApply addNew(JobSeekerApply jobSeekerApply) {
        return jobSeekerApplyRepository.save(jobSeekerApply);
    }
}
