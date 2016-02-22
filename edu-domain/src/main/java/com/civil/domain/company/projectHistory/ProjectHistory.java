package com.civil.domain.company.projectHistory;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by zaza on 2015/5/2.
 */
@Data
@Entity
@Table(name = "project_history")
@EqualsAndHashCode(of = { "projectHistoryId" })
public class ProjectHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_history_id")
    private Long projectHistoryId;

    @Column(name = "user_id", length = 100)
    private Long userId;

    @Column(name = "project_name", length = 200)
    private String projectName;

    @Column(name = "image_path", length = 200)
    private String imagePath;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "deleted", length = 1)
    private Boolean deleted = false;

}
