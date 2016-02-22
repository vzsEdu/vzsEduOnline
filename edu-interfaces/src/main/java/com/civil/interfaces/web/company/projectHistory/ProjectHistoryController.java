package com.civil.interfaces.web.company.projectHistory;


import com.civil.web.configuration.inteceptor.UserAccessCustom;
import com.google.common.base.Preconditions;
import com.vzs.Infrastructure.auth.User;
import com.vzs.application.qiniu.QiniuUploadServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by zaza on 8/16/2015.
 */
@Controller
//@Role("admin")
@RequestMapping(value = "/company/projectHistory")
public class ProjectHistoryController {

	@Value("#{environment['qiniu.bulkname.default']}")
	String bulkName;

	private static String imagePrefix = "company/projectHistory/";

	@Autowired
	private ProjectHistoryModifyProvider projectHistoryModifyProvider;
	@Autowired
	private ProjectHistoryRemoveProvider projectHistoryRemoveProvider;
	@Autowired
	private ProjectHistoryProvider projectHistoryViewProvider;

	@Autowired
	QiniuUploadServcie qiniuUploadServcie;

	@RequestMapping(value = "/saveProjectHistory", method = RequestMethod.POST)
	@UserAccessCustom(fieldName = "accountType", value = "1")
	@ResponseBody
//	public ModelAndView saveProjectHistory(User user, @ModelAttribute ProjectHistoryDto projectHistoryDto) {
		public ModelAndView saveProjectHistory(User user, @ModelAttribute ProjectHistoryDto projectHistoryDto, @RequestParam("projectFile") MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			String imagePath = imagePrefix + UUID.randomUUID() + ".jpg";
			qiniuUploadServcie.upload(file.getBytes(), bulkName, imagePath);
			projectHistoryDto.setImagePath(imagePath);
		}

		projectHistoryModifyProvider.createProjectHistoryByProjectHistoryDto(user.getUserId(), projectHistoryDto);
		return new ModelAndView(new RedirectView("/company/projectHistory/list/projectHistorys"));

	}

	@RequestMapping(value = "/view/projectHistory", method = RequestMethod.GET)
	@UserAccessCustom(fieldName = "accountType", value = "1")
	@ResponseBody
	public ModelAndView viewProjectHistory(User user) {
		ModelAndView modelAndView = new ModelAndView("/company/projectHistory/project_history_view");
		ProjectHistoryDto projectHistoryDto = projectHistoryViewProvider.getProjectHistoryDto(user.getUserId());
		if (projectHistoryDto == null) {
			return new ModelAndView("/company/projectHistory/project_history_view");
		}
		modelAndView.addObject("projectHistory", projectHistoryDto);
		return modelAndView;
	}

	@RequestMapping(value = "/list/projectHistorys", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView listProjectHistory(User user) {
		ModelAndView modelAndView = new ModelAndView("/company/projectHistory/project_history_list");

		return modelAndView;
	}

	@RequestMapping(value = "/list/my/projectHistorys", method = RequestMethod.GET)
	@UserAccessCustom(fieldName = "accountType", value = "1")
	@ResponseBody
	public List<ProjectHistoryDto> listmyProjectHistory(User user) {
		return projectHistoryViewProvider.findProjectHistorysByUserId(user.getUserId());
	}

	@RequestMapping(value = "/list/projectHistorysByCompany/{companyId}", method = RequestMethod.GET)
	@ResponseBody
	public List<ProjectHistoryDto> listProjectHistoryByCompanyId(@PathVariable("companyId") Long companyId) {
		return projectHistoryViewProvider.findProjectHistorysByCompanyId(companyId);
	}

	@RequestMapping(value = "/view/projectHistory/{projectHistoryId}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView viewProjectHistory(@PathVariable("projectHistoryId") Long projectHistoryId) {
		Preconditions.checkArgument(projectHistoryId != null, "ProjectHistory Id can't be null");
		ModelAndView modelAndView = new ModelAndView("/company/projectHistory/project_history_view");
		ProjectHistoryDto projectHistoryDto = projectHistoryViewProvider.getProjectHistoryDtoById(projectHistoryId);
		modelAndView.addObject("projectHistory", projectHistoryDto);
		return modelAndView;
	}


	@RequestMapping(value = "/remove/projectHistory/{projectHistoryId}", method = RequestMethod.GET)
	@UserAccessCustom(fieldName = "accountType", value = "1")
	@ResponseBody
	public ModelAndView removeProject(User user, @PathVariable("projectHistoryId") Long projectHistoryId) {
		projectHistoryRemoveProvider.removeById(user.getUserId(), projectHistoryId);
		return listProjectHistory(user);
	}

	@RequestMapping(value = "/create/projectHistory", method = RequestMethod.GET)
	@UserAccessCustom(fieldName = "accountType", value = "1")
	@ResponseBody
	public ModelAndView createProjectHistory() {
		ModelAndView modelAndView = new ModelAndView("/company/projectHistory/project_history_view");

		return modelAndView;
	}


}
