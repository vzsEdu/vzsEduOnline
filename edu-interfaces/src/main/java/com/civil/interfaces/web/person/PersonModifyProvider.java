package com.civil.interfaces.web.person;

import com.civil.domain.person.Person;
import com.civil.domain.person.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zaza on 2015/9/14.
 */
@Component
@Transactional
public class PersonModifyProvider {
	@Autowired
	private PersonService personService;

	public Long createPersonByPersonDto(Long userId, PersonDto personDto) {
		if (personDto.getPersonId() != null) {
			return updatePerson(userId, personDto);
		}
		Person person = personDto.convertToPerson(userId);
		person = personService.save(person);
		return person.getPersonId();
	}

	public Long updatePerson(Long userId, PersonDto personDto) {
		Person person = personService.findOwnPerson(userId, personDto.getPersonId());
		BeanUtils.copyProperties(personDto, person);
		personService.save(person);
		return person.getPersonId();
	}



}
