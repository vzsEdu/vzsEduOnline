package com.civil.interfaces.web.project.application;

import com.vzs.Infrastructure.auth.Role;
import com.vzs.Infrastructure.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by byao on 8/3/15.
 */
@Role("verified")
@RequestMapping(value = "/project-application/")
@Controller
public class ProjectApplicationController {
	@Autowired
	private ProjectApplicationProvider projectApplicationProvider;

	@RequestMapping(value = "/apply/projects/{projectId}", method = RequestMethod.POST)
	@ResponseBody
	public ProjectApplicationDto applyProject(User user, @PathVariable("projectId") long projectId, @RequestParam("comment") String comment) {
		return projectApplicationProvider.applyProject(user, projectId, comment);
	}

	@RequestMapping(value = "/apply/projects/{projectId}/project-application", method = RequestMethod.POST)
	@ResponseBody
	public ProjectApplicationDto findProjectApplication(User user, @PathVariable("projectId") long projectId) {
		return projectApplicationProvider.findProjectApplication(user, projectId);

	}

	@RequestMapping(value = "/cancel/projects/{projectId}", method = RequestMethod.GET)
	@ResponseBody
	public ProjectApplicationDto cancelProjectApplication(User user, @PathVariable("projectId") long projectId) {
		return projectApplicationProvider.cancelProjectApplication(user, projectId);

	}
}
