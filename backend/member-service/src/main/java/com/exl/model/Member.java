package com.exl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "internal_id")
	private Long internalId;
	
	@Column(name = "member_id")
	private String memberId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "formatted_name")
	private String formattedName;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;
	
	@Column(name = "address")
	private String address;
}
