package com.civil.interfaces.web.project;

import com.civil.domain.project.Project;
import com.civil.interfaces.web.project.approve.ProjectApproveProvider;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.vzs.Infrastructure.auth.Role;
import com.vzs.Infrastructure.auth.User;
import com.vzs.application.qiniu.QiniuUploadServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by byao on 6/20/15.
 */
//@Role("admin")
@Controller
@RequestMapping(value = "/project")
public class ProjectController {
	@Value("#{environment['qiniu.bulkname.default']}")
	String bulkName;

	private static String imagePrefix = "project/";

	@Autowired
	private ProjectModifyProvider projectModifyProvider;
	@Autowired
	private ProjectRemoveProvider projectRemoveProvider;
	@Autowired
	private ProjectViewProvider projectViewProvider;
	@Autowired
	private ProjectApproveProvider projectApproveProvider;

	@Autowired
	QiniuUploadServcie qiniuUploadServcie;

	@RequestMapping(value = "/saveProject", method = RequestMethod.POST)
	@Role("verified")
	@ResponseBody
	public ModelAndView saveProject(User user, HttpServletRequest request, @ModelAttribute ProjectDto projectDto, @RequestParam("projectFile")
	MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			String imagePath = imagePrefix + UUID.randomUUID() + ".jpg";
			qiniuUploadServcie.upload(file.getBytes(), bulkName, imagePath);
			projectDto.setImagePath(imagePath);
		}
		projectModifyProvider.createProjectByProjectDto(user.getUserId(), projectDto);
		return new ModelAndView(new RedirectView("/project/list/projects"));
	}

	@RequestMapping(value = "/view/project/{projectId}", method = RequestMethod.GET)
	@Role("verified")
	@ResponseBody
	public ModelAndView viewProject(User user, @PathVariable("projectId") Long projectId) {
		Preconditions.checkArgument(projectId != null, "Project Id can't be null");
		ModelAndView modelAndView = new ModelAndView("/project/project_view");
		ProjectDto projectDto = projectViewProvider.getProjectDto(user.getUserId(), projectId);
		modelAndView.addObject("project", projectDto);
		return modelAndView;
	}

	@RequestMapping(value = "/remove/project/{projectId}", method = RequestMethod.GET)
	@Role("verified")
	@ResponseBody
	public ModelAndView removeProject(User user, @PathVariable("projectId") Long projectId) {
		projectRemoveProvider.removeById(user.getUserId(), projectId);
		return listProject(user);
	}

	@RequestMapping(value = "/create/project", method = RequestMethod.GET)
	@Role("verified")
	@ResponseBody
	public ModelAndView createProject() {
		ModelAndView modelAndView = new ModelAndView("/project/project_view");

		return modelAndView;
	}

	@RequestMapping(value = "/list/projects", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView listProject(User user) {
		ModelAndView modelAndView = new ModelAndView("/project/project_list");

		return modelAndView;
	}

	@RequestMapping(value = "/list/my/projects", method = RequestMethod.GET)
	@Role("verified")
	@ResponseBody
	public List<ProjectDto> listmyProject(User user,
	                                      @RequestParam(defaultValue = "PROJECT_MY_PROJECTS") ProjectFunctionFlagEnum projectFunctionFlagEnum ) {
		if (ProjectFunctionFlagEnum.PROJECT_TO_BE_APPROVED == projectFunctionFlagEnum) {
			return projectApproveProvider.findMyApproveListProject(user.getUserId());
		} else if (ProjectFunctionFlagEnum.PROJECT_PUBLISHED == projectFunctionFlagEnum) {
			return projectViewProvider.findNonOwnPublishedProject(user.getUserId());
		} else  if (ProjectFunctionFlagEnum.PROJECT_MY_PROJECTS == projectFunctionFlagEnum){
			return projectViewProvider.findProjectsByUserId(user.getUserId());
		} else if (ProjectFunctionFlagEnum.PROJECT_MY_APPLIED == projectFunctionFlagEnum) {
			return projectViewProvider.findMyAppliedProjects(user.getUserId());
		}

		return Lists.newArrayList();
	}

	private ModelAndView getViewProjectModelAndView(Long projectId) {
		String viewUrl = String.format("/project/view/project/%s", projectId);
		return new ModelAndView(new RedirectView(viewUrl));
	}

	@RequestMapping(value = "/list/published/projects", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView listPublishedProject(User user) {
		ModelAndView modelAndView = new ModelAndView("/project/project_list");
		modelAndView.addObject("projectFunctionFlagEnum", ProjectFunctionFlagEnum.PROJECT_PUBLISHED);
		return modelAndView;
	}

	@RequestMapping(value = "/list/toBeApprove/projects", method = RequestMethod.GET)
	@Role("verified")
	@ResponseBody
	public ModelAndView listtoBeApproveProject(User user) {
		ModelAndView modelAndView = new ModelAndView("/project/project_list");
		modelAndView.addObject("projectFunctionFlagEnum", ProjectFunctionFlagEnum.PROJECT_TO_BE_APPROVED);
		return modelAndView;
	}

	@RequestMapping(value = "/list/myApplied/projects", method = RequestMethod.GET)
	@Role("verified")
	@ResponseBody
	public ModelAndView listMyAppliedProject(User user) {
		ModelAndView modelAndView = new ModelAndView("/project/project_list");
		modelAndView.addObject("projectFunctionFlagEnum", ProjectFunctionFlagEnum.PROJECT_MY_APPLIED);
		return modelAndView;
	}
}
