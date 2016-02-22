package com.civil.domain.person;

import com.vzs.utils.VzsCollectionUtils;
import com.vzs.utils.exception.VzsRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zaza at 2015/9/14
 */
@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;

	public Person save(Person person) {
		return personRepository.save(person);
	}

	public Person findPersonById(Long personId) {
		Person person = personRepository.findBypersonIdAndDeleted(personId, Boolean.FALSE);
		if (person == null) {
			throw new VzsRuntimeException("The person you queried is not existed");
		}
		return person;
	}

	public Person findOwnPerson(Long userId, Long personId) {
		Person person = personRepository.findBypersonIdAndDeleted(personId, Boolean.FALSE);
		if (person == null || person.getUserId() != userId) {
			throw new VzsRuntimeException("You can only operate with your own person");
		}
		return person;
	}

	public void deleteById(Long userId, Long personId) {
		Person person = personRepository.findOne(personId);
		if (person.getUserId() != userId) {
			throw new VzsRuntimeException("You can only delete your own person");
		}
		person.setDeleted(Boolean.TRUE);
		personRepository.save(person);
	}

	public List<Person> findPersonsByUserId(Long userId) {
		return personRepository.findByuserIdAndDeleted(userId, Boolean.FALSE);
	}

	public boolean hasPerson(Long userId) {
		return VzsCollectionUtils.isNotEmpty(personRepository.findByuserId(userId));
	}

	public Person getPersonByUserId(Long userId) {
		List<Person> personList = personRepository.findByuserId(userId);
		if (VzsCollectionUtils.isNotEmpty(personList)) {
			return personList.get(0);
		}
		return null;
	}

	public List<Person> findNonOwnVerifiedPerson(Long uesrId) {
		return personRepository.findByUserIdNotAndDeletedAndVerified(uesrId, Boolean.FALSE, Boolean.TRUE);
	}
}
