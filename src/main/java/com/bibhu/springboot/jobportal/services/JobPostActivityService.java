package com.bibhu.springboot.jobportal.services;

import com.bibhu.springboot.jobportal.entity.IRecruiterJobs;
import com.bibhu.springboot.jobportal.entity.JobCompany;
import com.bibhu.springboot.jobportal.entity.JobLocation;
import com.bibhu.springboot.jobportal.entity.JobPostActivity;
import com.bibhu.springboot.jobportal.entity.RecruiterJobsDTO;
import com.bibhu.springboot.jobportal.repository.JobPostActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;

    @Autowired
    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
    }

    public JobPostActivity saveJobPost(JobPostActivity jobPostActivity) {
        return jobPostActivityRepository.save(jobPostActivity);
    }

    public List<RecruiterJobsDTO> getRecruiterJobs(int recruiterId) {
        List<IRecruiterJobs> iRecruiterJobs = jobPostActivityRepository.getRecruiterJobs(recruiterId);
        List<RecruiterJobsDTO> recruiterJobsDTOs = new ArrayList<>();
        for (IRecruiterJobs rec : iRecruiterJobs) {
            JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getState(), rec.getCountry());
            JobCompany comp = new JobCompany(rec.getCompanyId(), rec.getName(), "");
            recruiterJobsDTOs.add(new RecruiterJobsDTO(rec.getTotalCandidates(), rec.getJob_post_id(), rec.getJob_title(), loc, comp));
        }
        return recruiterJobsDTOs;
    }
}
