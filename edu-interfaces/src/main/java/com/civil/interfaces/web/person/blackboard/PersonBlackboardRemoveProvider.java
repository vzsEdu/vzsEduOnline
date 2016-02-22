package com.civil.interfaces.web.person.blackboard;

import com.civil.domain.person.blackboard.PersonBlackboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zaza on 6/20/15.
 */
@Component
public class PersonBlackboardRemoveProvider {
	@Autowired
	private PersonBlackboardService personBlackboardService;

	public void removeById(Long userId, Long personBlackboardId) {
		personBlackboardService.deleteById(userId, personBlackboardId);
	}
}
