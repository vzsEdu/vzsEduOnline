package com.civil.domain.company.projectHistory;

import com.vzs.utils.VzsCollectionUtils;
import com.vzs.utils.exception.VzsRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by byao on 6/20/15.
 */
@Service
public class ProjectHistoryService {
	@Autowired
	private ProjectHistoryRepository projectHistoryRepository;

	public ProjectHistory save(ProjectHistory projectHistory) {
		return projectHistoryRepository.save(projectHistory);
	}

	public ProjectHistory findProjectHistoryById(Long projectHistoryId) {
		ProjectHistory projectHistory = projectHistoryRepository.findByprojectHistoryIdAndDeleted(projectHistoryId, Boolean.FALSE);
		if (projectHistory == null) {
			throw new VzsRuntimeException("The projectHistory you queried is not existed");
		}
		return projectHistory;
	}

	public ProjectHistory findOwnProjectHistory(Long userId, Long projectHistoryId) {
		ProjectHistory projectHistory = projectHistoryRepository.findByprojectHistoryIdAndDeleted(projectHistoryId, Boolean.FALSE);
		if (projectHistory == null || projectHistory.getUserId() != userId) {
			throw new VzsRuntimeException("You can only operate with your own projectHistory");
		}
		return projectHistory;
	}

//	public void deleteById(Long projectHistoryId) {
//		projectHistoryRepository.findByprojectHistoryIdAndDeleted(projectHistoryId, true);
//	}

	public void deleteById(Long userId, Long projectHistoryId) {

		ProjectHistory projectHistory = findProjectHistoryById(projectHistoryId);
		if (projectHistory.getUserId() != userId) {
			throw new VzsRuntimeException("You can only delete your own project");
		}
		projectHistory.setDeleted(Boolean.TRUE);
		projectHistoryRepository.save(projectHistory);
	}


	public List<ProjectHistory> findProjectHistorysByUserId(Long userId) {
		return projectHistoryRepository.findByuserIdAndDeleted(userId, Boolean.FALSE);
	}


	public List<ProjectHistory> findProjectHistorysByCompanyId(Long companyId) {
		return projectHistoryRepository.findProjectHistoryByCompanyId(companyId, Boolean.FALSE);
	}


	public boolean hasProjectHistory(Long userId) {
		return VzsCollectionUtils.isNotEmpty(projectHistoryRepository.findByuserId(userId));
	}

	public ProjectHistory getProjectHistoryByUserId(Long userId) {
		List<ProjectHistory> projectHistoryList = projectHistoryRepository.findByuserId(userId);
		if (VzsCollectionUtils.isNotEmpty(projectHistoryList)) {
			return projectHistoryList.get(0);
		}
		return null;
	}
}
