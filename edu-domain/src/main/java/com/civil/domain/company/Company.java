package com.civil.domain.company;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by zaza on 2015/5/2.
 */
@Data
@Entity
@Table(name = "company")
@EqualsAndHashCode(of = { "companyId" })
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "user_id", length = 100)
    private Long userId;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "company_name", length = 200)
    private String companyName;

    @Column(name = "industry_involved", length = 200)
    private String industryInvolved;

    @Column(name = "province", length = 100)
    private String province;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "dist", length = 100)
    private String dist;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "contactor", length = 50)
    private String contactor;

    @Column(name = "contact_no", length = 50)
    private String contactNo;

    @Column(name = "fax", length = 50)
    private String fax;

    @Column(name = "qualification", length = 1000)
    private String qualification;

    @Column(name = "self_introduction", length = 1000)
    private String selfIntroduction;

    @Column(name = "technical_support", length = 1000)
    private String technicalSupport;

    @Column(name = "verified", length = 1)
    private Boolean verified;

    @Column(name = "active_ind", length = 1)
    private Boolean activeInd;

    @Column(name = "deleted", length = 1)
    private Boolean deleted = false;

}
