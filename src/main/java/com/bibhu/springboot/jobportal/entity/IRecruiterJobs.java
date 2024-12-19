package com.bibhu.springboot.jobportal.entity;

public interface IRecruiterJobs {

    long getTotalCandidates();

    int getJob_post_id();

    String getJob_title();

    int getLocationId();

    String getCity();

    String getState();

    String getCountry();

    int getCompanyId();

    String getName();
}
