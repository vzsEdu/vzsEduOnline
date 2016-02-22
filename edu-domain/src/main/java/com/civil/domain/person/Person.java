package com.civil.domain.person;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by zaza on 2015/9/14.
 */
@Data
@Entity
@Table(name = "person")
@EqualsAndHashCode(of = { "personId" })
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "user_id", length = 100)
    private Long userId;

    @Column(name = "person_name", length = 200)
    private String personName;

    @Column(name = "enterprise", length = 200)
    private String enterprise;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "industry_involved", length = 200)
    private String industryInvolved;

    @Column(name = "work_experience", length = 2000)
    private String workExperience;

    @Column(name = "education_background", length = 2000)
    private String educationBackground;

    @Column(name = "certificate_skill", length = 2000)
    private String certificateSkill;

    @Column(name = "recommend_letter", length = 2000)
    private String recommendLetter;

    @Column(name = "verified", length = 1)
    private Boolean verified;

    @Column(name = "active_ind", length = 1)
    private Boolean activeInd;

    @Column(name = "deleted", length = 1)
    private Boolean deleted = false;

}
