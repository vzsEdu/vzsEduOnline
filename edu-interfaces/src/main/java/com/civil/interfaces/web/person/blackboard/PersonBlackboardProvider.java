package com.civil.interfaces.web.person.blackboard;

import com.civil.domain.person.blackboard.PersonBlackboard;
import com.civil.domain.person.blackboard.PersonBlackboardService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by byao on 6/25/15.
 */
@Component
public class PersonBlackboardProvider {
	@Autowired
	private PersonBlackboardService personBlackboardService;

	public PersonBlackboardDto getPersonBlackboardDto(Long userId) {
		PersonBlackboardDto personBlackboardDto = new PersonBlackboardDto();
		PersonBlackboard personBlackboard = personBlackboardService.getPersonBlackboardByUserId(userId);
		if (personBlackboard == null) {
			return null;
		}
		BeanUtils.copyProperties(personBlackboard, personBlackboardDto);
		return personBlackboardDto;
	}

	public List<PersonBlackboardDto> findPersonBlackboardsByUserId(Long userId) {
		List<PersonBlackboardDto> personBlackboardDtos = Lists.newArrayList();
		List<PersonBlackboard> personBlackboardsByUserId = personBlackboardService.findPersonBlackboardsByUserId(userId);
		for (PersonBlackboard personBlackboard : personBlackboardsByUserId) {
			PersonBlackboardDto personBlackboardDto = new PersonBlackboardDto();
			BeanUtils.copyProperties(personBlackboard, personBlackboardDto);
			personBlackboardDtos.add(personBlackboardDto);
		}
		return personBlackboardDtos;
	}

	public List<PersonBlackboardDto> findPersonBlackboardsByPersonId(Long personId) {
		List<PersonBlackboardDto> personBlackboardDtos = Lists.newArrayList();
		List<PersonBlackboard> personBlackboardsByUserId = personBlackboardService.findPersonBlackboardsByPersonId(personId);
		for (PersonBlackboard personBlackboard : personBlackboardsByUserId) {
			PersonBlackboardDto personBlackboardDto = new PersonBlackboardDto();
			BeanUtils.copyProperties(personBlackboard, personBlackboardDto);
			personBlackboardDtos.add(personBlackboardDto);
		}
		return personBlackboardDtos;
	}


	public PersonBlackboardDto getPersonBlackboardDtoById(Long personBlackboardId) {
		PersonBlackboardDto personBlackboardDto = new PersonBlackboardDto();
		PersonBlackboard personBlackboard = personBlackboardService.findPersonBlackboardById(personBlackboardId);

		BeanUtils.copyProperties(personBlackboard, personBlackboardDto);
		return personBlackboardDto;
	}
}
