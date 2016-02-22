package com.civil.interfaces.web.person;

import com.civil.domain.person.Person;
import com.civil.domain.person.PersonService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zaza on 2015/9/14.
 */
@Component
public class PersonViewProvider {
	@Autowired
	private PersonService personService;

	public PersonDto getPersonDto(Long userId) {
		PersonDto personDto = new PersonDto();
		Person person = personService.getPersonByUserId(userId);
		if (person == null) {
			return null;
		}
		BeanUtils.copyProperties(person, personDto);
		return personDto;
	}

	public List<PersonDto> findPersonsByUserId(Long userId) {
		List<PersonDto> personDtos = Lists.newArrayList();
		List<Person> personsByUserId = personService.findPersonsByUserId(userId);
		for (Person person : personsByUserId) {
			PersonDto personDto = new PersonDto();
			BeanUtils.copyProperties(person, personDto);
			personDtos.add(personDto);
		}
		return personDtos;
	}

	public PersonDto getPersonDtoById(Long personId) {
		PersonDto personDto = new PersonDto();
		Person person = personService.findPersonById(personId);

		BeanUtils.copyProperties(person, personDto);
		return personDto;
	}

	public List<PersonDto> findNonOwnVerifiedPerson(Long userId) {
		List<PersonDto> personDtos = Lists.newArrayList();
		List<Person> personsByUserId = personService.findNonOwnVerifiedPerson(userId);
		for (Person person : personsByUserId) {
			PersonDto personDto = new PersonDto();
			BeanUtils.copyProperties(person, personDto);
			personDtos.add(personDto);
		}
		return personDtos;
	}

}
