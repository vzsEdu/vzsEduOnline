package com.civil.domain.project.application;

import com.civil.domain.project.Project;
import com.mysema.query.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by byao on 10/31/15.
 */
//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
//@Table
//@EqualsAndHashCode(of = { "projectApplication" })
public class ProjectAndApplicationCombineDto implements Serializable{
	private ProjectApplication projectApplication;
	private Project project;
}
