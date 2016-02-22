package com.civil.interfaces.web.project.approve;

import com.vzs.Infrastructure.auth.Role;
import com.vzs.Infrastructure.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by byao on 10/31/15.
 */
@Role("verified")
@RequestMapping(value = "/project-approve/")
@Controller
public class ProjectApproveController {

	@Autowired
	private ProjectApproveProvider projectApproveProvider;

	@RequestMapping(value = "approve/projects/{projectId}", method = RequestMethod.POST)
	@ResponseBody
	public Boolean findProjectApplication(User user, @PathVariable("projectId") long projectId) {
		return projectApproveProvider.approveProject(user.getUserId(), projectId);
	}
}
