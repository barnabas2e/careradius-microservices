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

@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Long id;
	
	@Column (name = "username")
	private String username;
	
	@Column (name = "password")
	private String password;
	
	@Column (name = "first_name")
	private String name;
	
	@Column (name = "last_name")
	private String lastName;
	
	@Column (name = "formatted_name")
	private String formattedName;
	
	@Column (name = "email")
	private String email;
	
	@Enumerated(value = EnumType.STRING)
	@Column (name = "role")
	private Role role;
}
