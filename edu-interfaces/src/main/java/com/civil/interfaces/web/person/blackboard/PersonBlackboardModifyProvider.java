package com.civil.interfaces.web.person.blackboard;

import com.civil.domain.person.blackboard.PersonBlackboard;
import com.civil.domain.person.blackboard.PersonBlackboardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by byao on 6/20/15.
 */
@Component
@Transactional
public class PersonBlackboardModifyProvider {
	@Autowired
	private PersonBlackboardService personBlackboardService;

	public Long createPersonBlackboardByPersonBlackboardDto(Long userId, PersonBlackboardDto personBlackboardDto) {
		if (personBlackboardDto.getPersonBlackboardId() != null) {
			return updatePersonBlackboard(userId, personBlackboardDto);
		}
		PersonBlackboard personBlackboard = personBlackboardDto.convertToPersonBlackboard(userId);
		personBlackboard = personBlackboardService.save(personBlackboard);
		return personBlackboard.getPersonBlackboardId();
	}

	public Long updatePersonBlackboard(Long userId, PersonBlackboardDto personBlackboardDto) {
		PersonBlackboard personBlackboard = personBlackboardService.findOwnPersonBlackboard(userId, personBlackboardDto.getPersonBlackboardId());
		BeanUtils.copyProperties(personBlackboardDto, personBlackboard);
		personBlackboardService.save(personBlackboard);
		return personBlackboard.getPersonBlackboardId();
	}


}
