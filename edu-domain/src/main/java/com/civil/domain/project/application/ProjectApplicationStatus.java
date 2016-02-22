package com.civil.domain.project.application;

import lombok.Getter;

/**
 * Created by byao on 7/22/15.
 */
@Getter
public enum  ProjectApplicationStatus {
	NOT_APPLIED("project.application.no_applied"),//未申请
	APPLYING("project.application.applying"),//申请中
	APPLIED("project.application.applied"),//申请已受理
	APPLY_ACCEPTED("project.application.applied"),//申请采纳
	APPLY_REFUSED("project.application.apply_refused"),//申请拒绝
	APPLY_CANCELLED("project.application.apply_cancelled")//取消申请
	;

	String i18n;
	ProjectApplicationStatus (String i18n) {
		this.i18n = i18n;
	}
}
