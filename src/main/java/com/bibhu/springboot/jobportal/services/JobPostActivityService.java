package com.bibhu.springboot.jobportal.services;

import com.bibhu.springboot.jobportal.entity.IRecruiterJobs;
import com.bibhu.springboot.jobportal.entity.JobCompany;
import com.bibhu.springboot.jobportal.entity.JobLocation;
import com.bibhu.springboot.jobportal.entity.JobPostActivity;
import com.bibhu.springboot.jobportal.entity.RecruiterJobsDTO;
import com.bibhu.springboot.jobportal.exception.CustomJobPortalException;
import com.bibhu.springboot.jobportal.repository.JobPostActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public JobPostActivity getJobDetails(int id) {
        return jobPostActivityRepository.findById(id).orElseThrow(() -> new CustomJobPortalException("Requested Job not found."));
    }

    public List<JobPostActivity> getAllJobs() {
        return jobPostActivityRepository.findAll();
    }

    public List<JobPostActivity> searchPreferredJob(String job, String location,
                                                    List<String> types, List<String> remotes, LocalDate searchDate) {
        if (Objects.isNull(searchDate)) {
            return jobPostActivityRepository.searchWithoutDate(job, location, types, remotes);
        } else {
            return jobPostActivityRepository.searchWithDate(job, location, types, remotes, searchDate);
        }
    }
}
