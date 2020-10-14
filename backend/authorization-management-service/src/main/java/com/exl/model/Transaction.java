package com.exl.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="transaction")
@Data
public class Transaction implements Serializable {

	private static final long serialVersionUID = -580379719326023052L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="internal_id")
	private Long internalId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "authorization_internal_id", referencedColumnName = "internal_id")
	private Authorization authorization;
	
	@Column(name= "member_internal_id")
	private Long memberInternalId;
	
	@Column(name = "user_created_by_id")
	private Long createdBy;
	
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@Column(name = "user_updated_by_id")
	private Long updatedBy;
	
	@Column(name ="update_date")
	private LocalDateTime updateDate;
		
}
