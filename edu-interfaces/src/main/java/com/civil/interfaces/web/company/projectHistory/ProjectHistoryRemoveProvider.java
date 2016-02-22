package com.civil.interfaces.web.company.projectHistory;

import com.civil.domain.company.projectHistory.ProjectHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zaza on 6/20/15.
 */
@Component
public class ProjectHistoryRemoveProvider {
	@Autowired
	private ProjectHistoryService projectHistoryService;

	public void removeById(Long userId, Long projectHistoryId) {
		projectHistoryService.deleteById(userId, projectHistoryId);
	}
}
