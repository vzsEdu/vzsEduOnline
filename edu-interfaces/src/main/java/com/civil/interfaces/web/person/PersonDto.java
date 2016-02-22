package com.civil.interfaces.web.person;

import com.civil.domain.person.Person;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by zaza on 2015/9/14.
 */
@Data
public class PersonDto {

	private Long personId;

	private String personName;

	private String enterprise;

	private String location;

	private String industryInvolved;

	private String workExperience;

	private String educationBackground;

	private String certificateSkill;

	private String recommendLetter;

	private Boolean verified;

	public Person convertToPerson(Long userId) {
		Person person = new Person();
		BeanUtils.copyProperties(this, person);
		person.setUserId(userId);
//		person.setRegId(userId + "");
		//we don't have these two fields in design, just keep it for future
		return person;
	}
}
