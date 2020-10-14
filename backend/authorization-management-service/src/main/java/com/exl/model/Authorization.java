package com.exl.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
@Table(name="treatment_authorization")
@Data
public class Authorization implements Serializable {

	private static final long serialVersionUID = -951363292678734157L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="internal_id")
	private Long internalId;
	
	@Column(name = "reference_number")
	private String referenceNumber;
	
	@Column(name = "contact_name")
	private String contactName;
	
	@Column(name = "contact_phone")
	private String contactPhone;
	
	@Column(name = "contact_method")
	@Enumerated(value = EnumType.STRING)
	private ContactMethod contactMethod;
	
	@Column(name = "initiated_date")
	private Date dateInitiated;
	
	@Column(name = "valid_date_from")
	private Date dateValidFrom;
	
	@Column(name = "valid_date_to")
	private Date dateValidTo;	
	
}
