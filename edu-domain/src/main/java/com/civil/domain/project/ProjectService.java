package com.civil.domain.project;

import com.vzs.utils.exception.VzsRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by byao on 6/20/15.
 */
@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	public Project save(Project project) {
		return projectRepository.save(project);
	}

	public Project findOwnProject(Long userId, Long projectId) {
		Project project = projectRepository.findByprojectIdAndDeleted(projectId, Boolean.FALSE);
		if (project == null || !userId.equals(project.getUserId())) {
			throw new VzsRuntimeException("You can only operate with your own project");
		}
		return project;
	}

	public Project findProjectById(Long projectId) {
		return projectRepository.findOne(projectId);
	}

	public void deleteById(Long userId, Long projectId) {
		Project project = projectRepository.findOne(projectId);
		if (project.getUserId() != userId) {
			throw new VzsRuntimeException("You can only delete your own project");
		}
		project.setDeleted(Boolean.TRUE);
		projectRepository.save(project);
	}

	public List<Project> findProjectsByUserId(Long userId) {
		return projectRepository.findByuserIdAndDeleted(userId, Boolean.FALSE);
	}

	public List<Project> findNonOwnPublishedProject(Long uesrId) {
		return projectRepository.findByUserIdNotAndDeletedAndPublished(uesrId, Boolean.FALSE, Boolean.TRUE);
	}

	public List<Project> findMyAppliedProject(Long userId) {
		return projectRepository.findMyAppliedProject(userId);
	}
}
