package com.civil.interfaces.web.company;

import com.civil.domain.company.Company;
import com.civil.domain.company.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by byao on 6/20/15.
 */
@Component
@Transactional
public class CompanyModifyProvider {
	@Autowired
	private CompanyService companyService;

	public Long createCompanyByCompanyDto(Long userId, CompanyDto companyDto) {
		if (companyDto.getCompanyId() != null) {
			return updateCompany(userId, companyDto);
		}
		Company company = companyDto.convertToCompany(userId);
		company = companyService.save(company);
		return company.getCompanyId();
	}

	public Long updateCompany(Long userId, CompanyDto companyDto) {
		Company company = companyService.findOwnCompany(userId, companyDto.getCompanyId());
		BeanUtils.copyProperties(companyDto, company);
		companyService.save(company);
		return company.getCompanyId();
	}



}
