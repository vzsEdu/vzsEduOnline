package com.civil.domain.user;

import com.google.common.collect.Sets;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zaza on 2015/5/2.
 */
@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = { "usersId" })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login_id", length = 100)
    private String loginId;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "mobile", length = 30)
    private String mobile;

    @Column(name = "account_type", length = 1)
    private Integer accountType;

    @Column(name = "password", length = 256, nullable = false)
    private String password;

    @Column(name = "portrait", length = 200)
    private String portrait;

    @Column(name = "role_id", length = 20)
    private String roleId;

    @Column(name = "nick_name", length = 100)
    private String nickName;

    @Column(name = "verified", length = 1)
    private Integer verifiedLevel;

    @Column(name = "active_ind", length = 1)
    private Boolean activeInd;

    @Column(name = "email_verified", length = 1)
    private Boolean emailVerified;

    public Set<String> getRoleIds() {
        String[] split = roleId.split(",");
        return Sets.newHashSet(split);
    }

}
