package com.civil.domain.student;

import com.civil.domain.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by byao on 2/22/16.
 */
@Data
@Entity
@Table(name = "students")
@EqualsAndHashCode(of = { "studentId" })
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_id")
	private Long studentId;

	@Column(name = "name", length = 200, nullable = false)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_date", nullable = false)
	private Date  registerDate;

	@Column(name = "register_grade", nullable = false)
	private String registerGrade;

	@Column(name = "school", nullable = false)
	private String school;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birthday", nullable = false)
	private Date  birthday;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "gender", nullable = false)
	private Gender gender;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "e_mail", nullable = false)
	private String eMail;

	@Column(name = "parent_name")
	private String parentName;

	@Column(name = "parent_related")
	private String parentRelated;

	@Column(name = "parent_phone")
	private String parentPhone;

	@Column(name = "parent_weChat_id")
	private String parentWeChatId;




}
