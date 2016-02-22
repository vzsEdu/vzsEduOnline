package com.civil.interfaces.web.person;


import com.civil.interfaces.web.user.signup.SignupProvider;
import com.civil.web.configuration.inteceptor.UserAccessCustom;
import com.google.common.base.Preconditions;
import com.vzs.Infrastructure.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Created by zaza on 6/20/15.
 */
@Controller
//@Role("admin")
@RequestMapping(value = "/person")
public class PersonController {

	@Autowired
	private PersonModifyProvider personModifyProvider;

	@Autowired
	private PersonViewProvider personViewProvider;

	@Autowired
	private SignupProvider signupProvider;

	@RequestMapping(value = "/savePerson", method = RequestMethod.POST)
	@UserAccessCustom(fieldName = "accountType", value = "2")
	@ResponseBody
	public ModelAndView savePerson(User user, @ModelAttribute PersonDto personDto) {
		personModifyProvider.createPersonByPersonDto(user.getUserId(), personDto);
		if (user.getVerifiedLevel() == 0) {
			signupProvider.updateVerifyLevelByUserId(user.getUserId(), 2);
		}
		return getViewPersonModelAndView();
	}


	@RequestMapping(value = "/view/person", method = RequestMethod.GET)
	@UserAccessCustom(fieldName = "accountType", value = "2")
	@ResponseBody
	public ModelAndView viewPerson(User user) {
		ModelAndView modelAndView = new ModelAndView("/person/person_view");
		PersonDto personDto = personViewProvider.getPersonDto(user.getUserId());
		if (personDto == null) {
			return new ModelAndView("/person/person_view");
		}
		modelAndView.addObject("person", personDto);
		return modelAndView;
	}

	@RequestMapping(value = "/list/persons", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView listPerson(User user) {
		ModelAndView modelAndView = new ModelAndView("/person/person_list");

		return modelAndView;
	}

	@RequestMapping(value = "/list/other/persons", method = RequestMethod.GET)
	@ResponseBody
	public List<PersonDto> listmyPerson(User user) {
		return personViewProvider.findNonOwnVerifiedPerson(user.getUserId());

	}

	private ModelAndView getViewPersonModelAndView() {
		return new ModelAndView(new RedirectView("/person/view/person"));
	}

	@RequestMapping(value = "/view/person/{personId}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView viewPerson(@PathVariable("personId") Long personId) {
		Preconditions.checkArgument(personId != null, "ProjectHistory Id can't be null");
		ModelAndView modelAndView = new ModelAndView("/person/person_view_readonly");
		PersonDto personDto = personViewProvider.getPersonDtoById(personId);
		modelAndView.addObject("person", personDto);
		return modelAndView;
	}


}
