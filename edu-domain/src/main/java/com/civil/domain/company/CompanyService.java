package com.civil.domain.company;

import com.vzs.utils.VzsCollectionUtils;
import com.vzs.utils.exception.VzsRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by byao on 6/20/15.
 */
@Service
public class CompanyService {
	@Autowired
	private CompanyRepository companyRepository;

	public Company save(Company company) {
		return companyRepository.save(company);
	}

	public Company findCompanyById(Long companyId) {
		Company company = companyRepository.findBycompanyIdAndDeleted(companyId, Boolean.FALSE);
		if (company == null) {
			throw new VzsRuntimeException("The company you queried is not existed");
		}
		return company;
	}

	public Company findOwnCompany(Long userId, Long companyId) {
		Company company = companyRepository.findBycompanyIdAndDeleted(companyId, Boolean.FALSE);
		if (company == null || company.getUserId() != userId) {
			throw new VzsRuntimeException("You can only operate with your own company");
		}
		return company;
	}

	public void deleteById(Long userId, Long companyId) {
		Company company = companyRepository.findOne(companyId);
		if (company.getUserId() != userId) {
			throw new VzsRuntimeException("You can only delete your own company");
		}
		company.setDeleted(Boolean.TRUE);
		companyRepository.save(company);
	}

	public List<Company> findCompanysByUserId(Long userId) {
		return companyRepository.findByuserIdAndDeleted(userId, Boolean.FALSE);
	}

	public boolean hasCompany(Long userId) {
		return VzsCollectionUtils.isNotEmpty(companyRepository.findByuserId(userId));
	}

	public Company getCompanyByUserId(Long userId) {
		List<Company> companyList = companyRepository.findByuserId(userId);
		if (VzsCollectionUtils.isNotEmpty(companyList)) {
			return companyList.get(0);
		}
		return null;
	}

	public List<Company> findNonOwnVerifiedCompany(Long uesrId) {
		return companyRepository.findByUserIdNotAndDeletedAndVerified(uesrId, Boolean.FALSE, Boolean.TRUE);
	}
}
