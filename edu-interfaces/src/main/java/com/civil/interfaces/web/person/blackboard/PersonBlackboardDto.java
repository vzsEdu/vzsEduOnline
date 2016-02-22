package com.civil.interfaces.web.person.blackboard;

import com.civil.domain.person.blackboard.PersonBlackboard;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by zaza on 8/16/15.
 */
@Data
public class PersonBlackboardDto {

	private Long personBlackboardId;

	private String blackboard;

	public PersonBlackboard convertToPersonBlackboard(Long userId) {
		PersonBlackboard personBlackboard = new PersonBlackboard();
		BeanUtils.copyProperties(this, personBlackboard);
		personBlackboard.setUserId(userId);
//		company.setRegId(userId + "");
		//we don't have these two fields in design, just keep it for future
		return personBlackboard;
	}
}
