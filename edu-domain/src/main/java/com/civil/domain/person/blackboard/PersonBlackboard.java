package com.civil.domain.person.blackboard;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by zaza on 2015/10/1.
 */
@Data
@Entity
@Table(name = "person_blackboard")
@EqualsAndHashCode(of = { "personBlackboardId" })
public class PersonBlackboard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_blackboard_id")
    private Long personBlackboardId;

    @Column(name = "user_id", length = 100)
    private Long userId;

    @Column(name = "blackboard")
    private String blackboard;

    @Column(name = "deleted", length = 1)
    private Boolean deleted = false;

}
