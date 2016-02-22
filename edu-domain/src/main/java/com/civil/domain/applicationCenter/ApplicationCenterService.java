package com.civil.domain.applicationCenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by byao on 11/8/15.
 */
@Service
public class ApplicationCenterService {
	@Autowired
	private ApplicationCenterRepository applicationCenterRepository;

	public List<ApplicationCenter> findByUserId(Long userId) {
		return applicationCenterRepository.findByUserId(userId);
	}

	public List<ApplicationCenter> findOthersByUserId(Long userId) {
		return applicationCenterRepository.findByUserIdNot(userId);
	}

	public ApplicationCenter findByApplicationId(Long applicationId) {
		return applicationCenterRepository.findOne(applicationId);
	}

	public ApplicationCenter findOwnApplication(Long userId, Long applicationCenterId) {
		return applicationCenterRepository.findByUserIdAndApplicationCenterId(userId, applicationCenterId);
	}

	public void save(ApplicationCenter applicationCenter) {
		applicationCenterRepository.save(applicationCenter);
	}
}
