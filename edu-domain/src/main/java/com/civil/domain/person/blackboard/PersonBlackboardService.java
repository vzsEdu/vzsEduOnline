package com.civil.domain.person.blackboard;

import com.vzs.utils.VzsCollectionUtils;
import com.vzs.utils.exception.VzsRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zaza on 10/1/15.
 */
@Service
public class PersonBlackboardService {
	@Autowired
	private PersonBlackboardRepository personBlackboardRepository;

	public PersonBlackboard save(PersonBlackboard personBlackboard) {
		return personBlackboardRepository.save(personBlackboard);
	}

	public PersonBlackboard findPersonBlackboardById(Long personBlackboardId) {
		PersonBlackboard personBlackboard = personBlackboardRepository.findBypersonBlackboardIdAndDeleted(personBlackboardId, Boolean.FALSE);
		if (personBlackboard == null) {
			throw new VzsRuntimeException("The personBlackboard you queried is not existed");
		}
		return personBlackboard;
	}

	public PersonBlackboard findOwnPersonBlackboard(Long userId, Long personBlackboardId) {
		PersonBlackboard personBlackboard = personBlackboardRepository.findBypersonBlackboardIdAndDeleted(personBlackboardId, Boolean.FALSE);
		if (personBlackboard == null || personBlackboard.getUserId() != userId) {
			throw new VzsRuntimeException("You can only operate with your own personBlackboard");
		}
		return personBlackboard;
	}

//	public void deleteById(Long personBlackboardId) {
//		personBlackboardRepository.findBypersonBlackboardIdAndDeleted(personBlackboardId, true);
//	}

	public void deleteById(Long userId, Long personBlackboardId) {

		PersonBlackboard personBlackboard = findPersonBlackboardById(personBlackboardId);
		if (personBlackboard.getUserId() != userId) {
			throw new VzsRuntimeException("You can only delete your own project");
		}
		personBlackboard.setDeleted(Boolean.TRUE);
		personBlackboardRepository.save(personBlackboard);
	}


	public List<PersonBlackboard> findPersonBlackboardsByUserId(Long userId) {
		return personBlackboardRepository.findByuserIdAndDeleted(userId, Boolean.FALSE);
	}

	public List<PersonBlackboard> findPersonBlackboardsByPersonId(Long personId) {
		return personBlackboardRepository.findPersonBlackboardsByPersonId(personId, Boolean.FALSE);
	}

	public boolean hasPersonBlackboard(Long userId) {
		return VzsCollectionUtils.isNotEmpty(personBlackboardRepository.findByuserId(userId));
	}

	public PersonBlackboard getPersonBlackboardByUserId(Long userId) {
		List<PersonBlackboard> personBlackboardList = personBlackboardRepository.findByuserId(userId);
		if (VzsCollectionUtils.isNotEmpty(personBlackboardList)) {
			return personBlackboardList.get(0);
		}
		return null;
	}
}
