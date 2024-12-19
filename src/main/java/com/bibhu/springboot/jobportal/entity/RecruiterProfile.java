package com.bibhu.springboot.jobportal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "recruiter_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecruiterProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int userAccountId;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    @MapsId
    private Users userId;

    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String country;
    private String company;

    @Column(nullable = true, length = 64)
    private String profilePhoto;

    public RecruiterProfile(Users userId) {
        this.userId = userId;
    }

    @Transient
    public String getPhotosImagePath() {
        if (profilePhoto == null)
            return null;
        return "/photos/recruiter/" + userAccountId + "/" + profilePhoto;
    }
}
