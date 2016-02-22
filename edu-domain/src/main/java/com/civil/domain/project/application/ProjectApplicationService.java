package com.civil.domain.project.application;

import com.vzs.utils.VzsStringUtils;
import com.vzs.utils.exception.VzsRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static  com.civil.domain.project.application.ProjectApplicationStatus.*;
/**
 * Created by byao on 7/22/15.
 */
@Service
public class ProjectApplicationService {
	@Autowired
	private ProjectApplicationRepository projectApplicationRepository;

	public ProjectApplication save(ProjectApplication projectApplication) {
		projectApplication.setLastUpdateTime(new Date());
		return projectApplicationRepository.save(projectApplication);
	}

	public List<ProjectApplication> findMyProjectApplications(Long userId) {
		return projectApplicationRepository.findByUserId(userId);
	}

	public boolean updateStatus(Long projectApplicationid, ProjectApplicationStatus projectApplicationStatus) {
		ProjectApplication projectApplication = projectApplicationRepository.findOne(projectApplicationid);
		if (projectApplication == null) {
			throw new VzsRuntimeException("There is no project application for this one, any concern let Admin know");
		}
		projectApplication.setProjectApplicationStatus(projectApplicationStatus);
		save(projectApplication);
		return true;
	}

	public ProjectApplication findProjectApplictionByUserIdAndProjectId(Long userId, Long projectId) {
		return projectApplicationRepository.findByUserIdAndProjectId(userId, projectId);
	}

	public ProjectApplication cancelProject(Long userId, Long projectId) {
		ProjectApplication byUserIdAndProjectId = findProjectApplictionByUserIdAndProjectId(userId, projectId);
		if (byUserIdAndProjectId == null) {
			return null;
		}
		if (ProjectApplicationStatus.APPLYING != byUserIdAndProjectId.getProjectApplicationStatus()) {
			throw new VzsRuntimeException("can't Cancel");
		}
		ProjectApplication projectApplication = byUserIdAndProjectId;
		projectApplication.setComment(VzsStringUtils.EMPTY);
		projectApplication.setProjectApplicationStatus(ProjectApplicationStatus.APPLY_CANCELLED);
		return save(projectApplication);
	}

	private boolean validateApplyProject(ProjectApplicationStatus projectApplicationStatus) {
		if (projectApplicationStatus == ProjectApplicationStatus.APPLY_ACCEPTED ||
				projectApplicationStatus == ProjectApplicationStatus.APPLY_REFUSED) {
			return false;
		}
		return true;
	}

	public ProjectApplication applyProject(Long userId, Long projectId, String comment) {
		ProjectApplication byUserIdAndProjectId = findProjectApplictionByUserIdAndProjectId(userId, projectId);
		ProjectApplication projectApplication = byUserIdAndProjectId == null ? new ProjectApplication() : byUserIdAndProjectId;

		boolean validateApplyProject = validateApplyProject(projectApplication.getProjectApplicationStatus());
		if (!validateApplyProject) {
			throw new VzsRuntimeException("Error");
		}

		projectApplication.setUserId(userId);
		projectApplication.setProjectId(projectId);
		projectApplication.setComment(comment);
		projectApplication.setProjectApplicationStatus(ProjectApplicationStatus.APPLYING);
		return save(projectApplication);
	}

	public List<ProjectAndApplicationCombineDto> toBeApproveProject(Long userId) {
		return projectApplicationRepository.findMyToBeApprovePrjects(userId, APPLYING, APPLY_ACCEPTED, APPLY_REFUSED);
	}

	public List<ProjectAndApplicationCombineDto> toBeApproveProjectValid(Long userId, Long projectid) {
		return projectApplicationRepository.findMyToBeApproveValidPrjects(userId, projectid, APPLYING,  APPLY_ACCEPTED);
	}

	public void acceptApplicationStatus(Long projectApplicationId) {
		ProjectApplication projectApplication = projectApplicationRepository.findOne(projectApplicationId);
		if (projectApplication != null) {
			projectApplication.setProjectApplicationStatus(APPLY_ACCEPTED);
			projectApplicationRepository.save(projectApplication);
		}
	}

}
