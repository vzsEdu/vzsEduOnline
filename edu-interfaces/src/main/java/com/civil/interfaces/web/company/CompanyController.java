package com.civil.interfaces.web.company;


import com.civil.interfaces.web.user.signup.SignupProvider;
import com.civil.web.configuration.inteceptor.UserAccessCustom;
import com.google.common.base.Preconditions;
import com.vzs.Infrastructure.auth.Role;
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
//@Role("company")
@RequestMapping(value = "/company")
public class CompanyController {

	@Autowired
	private CompanyModifyProvider companyModifyProvider;

	@Autowired
	private CompanyViewProvider companyViewProvider;

	@Autowired
	private SignupProvider signupProvider;

	@RequestMapping(value = "/saveCompany", method = RequestMethod.POST)
	@UserAccessCustom(fieldName = "accountType", value = "1")
	@ResponseBody
	public ModelAndView saveCompany(User user, @ModelAttribute  CompanyDto companyDto) {
		companyModifyProvider.createCompanyByCompanyDto(user.getUserId(), companyDto);
		if (user.getVerifiedLevel() == 0) {
			signupProvider.updateVerifyLevelByUserId(user.getUserId(), 2);
		}
		return getViewCompanyModelAndView();
}

	@RequestMapping(value = "/view/company", method = RequestMethod.GET)
	@UserAccessCustom(fieldName = "accountType", value = "1")
	@ResponseBody
	public ModelAndView viewCompany(User user) {
		ModelAndView modelAndView = new ModelAndView("/company/company_view");
		CompanyDto companyDto = companyViewProvider.getCompanyDto(user.getUserId());
		if (companyDto == null) {
			return new ModelAndView("/company/company_view");
		}
		modelAndView.addObject("company", companyDto);
		return modelAndView;
	}

	@RequestMapping(value = "/list/companys", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView listCompany(User user) {
		ModelAndView modelAndView = new ModelAndView("/company/company_list");

		return modelAndView;
	}

	@RequestMapping(value = "/list/other/companys", method = RequestMethod.GET)
	@ResponseBody
	public List<CompanyDto> listmyCompany(User user) {
		return companyViewProvider.findNonOwnVerifiedCompany(user.getUserId());
	}

	private ModelAndView getViewCompanyModelAndView() {
		return new ModelAndView(new RedirectView("/company/view/company"));
	}

	@RequestMapping(value = "/view/company/{companyId}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView viewCompany(@PathVariable("companyId") Long companyId) {
		Preconditions.checkArgument(companyId != null, "companyHistory Id can't be null");
		ModelAndView modelAndView = new ModelAndView("/company/company_view_readonly");
		CompanyDto companyDto = companyViewProvider.getCompanyDtoById(companyId);
		modelAndView.addObject("company", companyDto);
		return modelAndView;
	}

}
