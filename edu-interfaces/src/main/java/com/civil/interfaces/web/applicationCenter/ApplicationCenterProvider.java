package com.civil.interfaces.web.applicationCenter;

import com.civil.domain.applicationCenter.ApplicationCenter;
import com.civil.domain.applicationCenter.ApplicationCenterService;
import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.vzs.utils.exception.VzsRuntimeException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by byao on 11/8/15.
 */
@Component
public class ApplicationCenterProvider {
	@Autowired
	private ApplicationCenterService applicationCenterService;

	public List<ApplicationCenterDto> findOwnApplication(Long userId) {
		List<ApplicationCenter> applicationCenters = applicationCenterService.findByUserId(userId);
		return transferFromApplicationCenter(applicationCenters);
	}

	public List<ApplicationCenterDto> findOthers(Long userId) {
		List<ApplicationCenter> applicationCenters = applicationCenterService.findOthersByUserId(userId);
		return transferFromApplicationCenter(applicationCenters);
	}

	private List<ApplicationCenterDto> transferFromApplicationCenter(List<ApplicationCenter> applicationCenters) {
		return Lists.newArrayList(Iterators.transform(applicationCenters.iterator(), new Function<ApplicationCenter, ApplicationCenterDto>() {
			@Override
			public ApplicationCenterDto apply(ApplicationCenter input) {
				return ApplicationCenterDto.transferFrom(input);
			}
		}));
	}

	public ApplicationCenterDto findByApplicationId(Long userId, Long applicationId) {
		ApplicationCenter applicationCenter = applicationCenterService.findByApplicationId(applicationId);
		return ApplicationCenterDto.transferFrom(applicationCenter);
	}

	public void save(Long userId, ApplicationCenterDto applicationCenterDto) {
		if (applicationCenterDto.getApplicationCenterId() != null) {
			ApplicationCenter applicationCenter = applicationCenterService.findOwnApplication(userId, applicationCenterDto.getApplicationCenterId());
			if (applicationCenter == null) {
				throw new VzsRuntimeException("the Application which you modify is not yours or not created");
			}
			BeanUtils.copyProperties(applicationCenterDto, applicationCenter);
			applicationCenterService.save(applicationCenter);
		} else {
			ApplicationCenter applicationCenter = applicationCenterDto.convertToProject(userId);
			applicationCenterService.save(applicationCenter);
		}
	}
}
