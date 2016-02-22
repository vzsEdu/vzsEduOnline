package com.civil.interfaces.web.company;

import com.civil.domain.company.Company;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by zaza on 6/21/15.
 */
@Data
public class CompanyDto {

	private Long companyId;

	private String email;

	private String companyName;

	private String industryInvolved;

	private String province;

	private String city;

	private String dist;

	private String address;

	private String contactor;

	private String contactNo;

	private String fax;

	private String qualification;

	private String selfIntroduction;

	private String technicalSupport;

	private Boolean verified;

	public Company convertToCompany(Long userId) {
		Company company = new Company();
		BeanUtils.copyProperties(this, company);
		company.setUserId(userId);
//		company.setRegId(userId + "");
		//we don't have these two fields in design, just keep it for future
		return company;
	}
}
