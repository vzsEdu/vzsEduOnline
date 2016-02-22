package com.civil.interfaces.web.person.blackboard;


import com.civil.web.configuration.inteceptor.UserAccessCustom;
import com.google.common.base.Preconditions;
import com.vzs.Infrastructure.auth.Role;
import com.vzs.Infrastructure.auth.User;
import com.vzs.application.qiniu.QiniuUploadServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

/**
 * Created by zaza on 8/16/2015.
 */
@Controller
//@Role("admin")
@RequestMapping(value = "/person/blackboard")
public class PersonBlackboardController {

	@Value("#{environment['qiniu.bulkname.default']}")
	String bulkName;


	@Autowired
	private PersonBlackboardModifyProvider personBlackboardModifyProvider;
	@Autowired
	private PersonBlackboardRemoveProvider personBlackboardRemoveProvider;
	@Autowired
	private PersonBlackboardProvider personBlackboardViewProvider;

	@Autowired
	QiniuUploadServcie qiniuUploadServcie;

	@RequestMapping(value = "/savePersonBlackboard", method = RequestMethod.POST)
	@UserAccessCustom(fieldName = "accountType", value = "2")
	@ResponseBody
//	public ModelAndView savePersonBlackboard(User user, @ModelAttribute PersonBlackboardDto personBlackboardDto) {
		public ModelAndView savePersonBlackboard(User user, @ModelAttribute PersonBlackboardDto personBlackboardDto) throws IOException {
//		if (!file.isEmpty()) {
//			String imagePath = imagePrefix + UUID.randomUUID() + ".jpg";
//			qiniuUploadServcie.upload(file.getBytes(), bulkName, imagePath);
//			personBlackboardDto.setImagePath(imagePath);
//		}

		personBlackboardModifyProvider.createPersonBlackboardByPersonBlackboardDto(user.getUserId(), personBlackboardDto);
		return new ModelAndView(new RedirectView("/person/blackboard/list/personBlackboards"));

	}

	@RequestMapping(value = "/view/personBlackboard", method = RequestMethod.GET)
	@UserAccessCustom(fieldName = "accountType", value = "2")
	@ResponseBody
	public ModelAndView viewPersonBlackboard(User user) {
		ModelAndView modelAndView = new ModelAndView("/person/blackboard/person_blackboard_view");
		PersonBlackboardDto personBlackboardDto = personBlackboardViewProvider.getPersonBlackboardDto(user.getUserId());
		if (personBlackboardDto == null) {
			return new ModelAndView("/person/blackboard/person_blackboard_view");
		}
		modelAndView.addObject("personBlackboard", personBlackboardDto);
		return modelAndView;
	}

	@RequestMapping(value = "/list/personBlackboards", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView listPersonBlackboard(User user) {
		ModelAndView modelAndView = new ModelAndView("/person/blackboard/person_blackboard_list");

		return modelAndView;
	}

	@RequestMapping(value = "/list/my/personBlackboards", method = RequestMethod.GET)
	@UserAccessCustom(fieldName = "accountType", value = "2")
	@ResponseBody
	public List<PersonBlackboardDto> listmyPersonBlackboard(User user) {
		return personBlackboardViewProvider.findPersonBlackboardsByUserId(user.getUserId());
	}
	@RequestMapping(value = "/list/personBlackboardsByPerson/{personId}", method = RequestMethod.GET)
	@ResponseBody
	public List<PersonBlackboardDto> listPersonBlackboardByPersonId(@PathVariable("personId") Long personId) {
		return personBlackboardViewProvider.findPersonBlackboardsByPersonId(personId);
	}
	@RequestMapping(value = "/view/personBlackboard/{personBlackboardId}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView viewPersonBlackboard(@PathVariable("personBlackboardId") Long personBlackboardId) {
		Preconditions.checkArgument(personBlackboardId != null, "PersonBlackboard Id can't be null");
		ModelAndView modelAndView = new ModelAndView("/person/blackboard/person_blackboard_view");
		PersonBlackboardDto personBlackboardDto = personBlackboardViewProvider.getPersonBlackboardDtoById(personBlackboardId);
		modelAndView.addObject("personBlackboard", personBlackboardDto);
//		modelAndView.addObject("personBlackboard.blackboard", personBlackboardDto.getBlackboard());
		return modelAndView;
	}


	@RequestMapping(value = "/remove/personBlackboard/{personBlackboardId}", method = RequestMethod.GET)
	@UserAccessCustom(fieldName = "accountType", value = "2")
	@ResponseBody
	public ModelAndView removeProject(User user, @PathVariable("personBlackboardId") Long personBlackboardId) {
		personBlackboardRemoveProvider.removeById(user.getUserId(), personBlackboardId);
		return listPersonBlackboard(user);
	}

	@RequestMapping(value = "/create/personBlackboard", method = RequestMethod.GET)
	@UserAccessCustom(fieldName = "accountType", value = "2")
	@ResponseBody
	public ModelAndView createPersonBlackboard() {
		ModelAndView modelAndView = new ModelAndView("/person/blackboard/person_blackboard_view");

		return modelAndView;
	}


}
