package com.civil.interfaces.web.applicationCenter;

import com.civil.interfaces.web.project.ProjectDto;
import com.civil.interfaces.web.project.ProjectFunctionFlagEnum;
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

import static com.civil.interfaces.web.applicationCenter.ApplicationCenterFunctionEnmu.*;
/**
 * Created by byao on 11/8/15.
 */
@RequestMapping(value = "/application-center/")
@Controller
public class ApplicationCenterController {
	@Value("#{environment['qiniu.bulkname.default']}")
	String bulkName;

	private static String imagePrefix = "application-center/";

	@Autowired
	private ApplicationCenterProvider applicationCenterProvider;

	@Autowired
	QiniuUploadServcie qiniuUploadServcie;

	@RequestMapping(value = "data/lists", method = RequestMethod.GET)
	@ResponseBody
	public List<ApplicationCenterDto> findApplication(User user,
	                                                  @RequestParam(defaultValue = "OWN_APPLICATION") ApplicationCenterFunctionEnmu applicationCenterFunctionEnmu) {
		if (OWN_APPLICATION == applicationCenterFunctionEnmu) {
			return applicationCenterProvider.findOwnApplication(user.getUserId());
		} else if (OTHERS_APPLICATION == applicationCenterFunctionEnmu) {
			return applicationCenterProvider.findOthers(user.getUserId());
		}

		return Lists.newArrayList();
	}

	@RequestMapping(value = "/own/lists", method = RequestMethod.GET)
	@Role("verified")
	@ResponseBody
	public ModelAndView listOwnApplicationCenter() {
		ModelAndView modelAndView = new ModelAndView("/applicationCenter/application_center_list");
		modelAndView.addObject("applicationCenterFunctionEnmu", OWN_APPLICATION);
		return modelAndView;
	}

	@RequestMapping(value = "/others/lists", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView listOthersApplicationCenter() {
		ModelAndView modelAndView = new ModelAndView("/applicationCenter/application_center_list");
		modelAndView.addObject("applicationCenterFunctionEnmu", OTHERS_APPLICATION);
		return modelAndView;
	}

	@RequestMapping(value = "/application/save", method = RequestMethod.POST)
	@Role("verified")
	public ModelAndView saveApplication(User user, @ModelAttribute ApplicationCenterDto applicationCenterDto, @RequestParam("applicationFile")
	MultipartFile file) throws IOException {
		ModelAndView modelAndView = new ModelAndView("/applicationCenter/application_center_view");
		if (!file.isEmpty()) {
			String imagePath = imagePrefix + UUID.randomUUID() + ".jpg";
			qiniuUploadServcie.upload(file.getBytes(), bulkName, imagePath);
			applicationCenterDto.setImagePath(imagePath);
		}

		applicationCenterProvider.save(user.getUserId(), applicationCenterDto);
		return new ModelAndView(new RedirectView("/application-center/own/lists"));
	}

	@RequestMapping(value = "/view/application/{applicationId}", method = RequestMethod.GET)
	@Role("verified")
	public ModelAndView viewProject(User user, @PathVariable("applicationId") Long applicationId) {
		ModelAndView modelAndView = new ModelAndView("/applicationCenter/application_center_view");
		ApplicationCenterDto applicationCenterDto = applicationCenterProvider.findByApplicationId(user.getUserId(), applicationId);
		modelAndView.addObject("project", applicationCenterDto);
		return modelAndView;
	}

	@RequestMapping(value = "/view/create", method = RequestMethod.GET)
	@Role("verified")
	public ModelAndView viewProject() {
		ModelAndView modelAndView = new ModelAndView("/applicationCenter/application_center_view");
		return modelAndView;
	}
}
