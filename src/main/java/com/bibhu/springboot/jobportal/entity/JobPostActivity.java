package com.bibhu.springboot.jobportal.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "job_post_activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobPostActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int jobPostId;

    @Length(max = 10000)
    private String descriptionOfJob;

    private String jobTitle;
    private String jobType;

    @DateTimeFormat(pattern = "dd-MM-YYYY")
    private Date postedDate;

    private String remote;
    private String salary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobCompanyId", referencedColumnName = "id")
    private JobCompany jobCompanyId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobLocationId", referencedColumnName = "id")
    private JobLocation jobLocationId;

    @ManyToOne
    @JoinColumn(name = "postedById", referencedColumnName = "userId")
    private Users postedById;

    @Transient
    private boolean isActive;

    @Transient
    private boolean isSaved;
}