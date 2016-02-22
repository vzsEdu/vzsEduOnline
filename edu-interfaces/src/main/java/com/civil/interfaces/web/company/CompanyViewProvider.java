package com.civil.interfaces.web.company;

import com.civil.domain.company.Company;
import com.civil.domain.company.CompanyService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by byao on 6/25/15.
 */
@Component
public class CompanyViewProvider {
	@Autowired
	private CompanyService companyService;

	public CompanyDto getCompanyDto(Long userId) {
		CompanyDto companyDto = new CompanyDto();
		Company company = companyService.getCompanyByUserId(userId);
		if (company == null) {
			return null;
		}
		BeanUtils.copyProperties(company, companyDto);
		return companyDto;
	}

	public List<CompanyDto> findCompanysByUserId(Long userId) {
		List<CompanyDto> companyDtos = Lists.newArrayList();
		List<Company> companysByUserId = companyService.findCompanysByUserId(userId);
		for (Company company : companysByUserId) {
			CompanyDto companyDto = new CompanyDto();
			BeanUtils.copyProperties(company, companyDto);
			companyDtos.add(companyDto);
		}
		return companyDtos;
	}

	public CompanyDto getCompanyDtoById(Long companyId) {
		CompanyDto companyDto = new CompanyDto();
		Company company = companyService.findCompanyById(companyId);

		BeanUtils.copyProperties(company, companyDto);
		return companyDto;
	}

	public List<CompanyDto> findNonOwnVerifiedCompany(Long userId) {
		List<CompanyDto> companyDtos = Lists.newArrayList();
		List<Company> companysByUserId = companyService.findNonOwnVerifiedCompany(userId);
		for (Company company : companysByUserId) {
			CompanyDto companyDto = new CompanyDto();
			BeanUtils.copyProperties(company, companyDto);
			companyDtos.add(companyDto);
		}
		return companyDtos;
	}
}
