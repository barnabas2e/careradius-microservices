package com.exl.model;

public enum ContactMethod { 
	
  // email
  EMAIL("EMAIL", "Email"),
  // fax
  FAX("FAX", "Fax"),
  // phone
  PHONE("PHONE", "Phone"),
  // video conferencing
  VIDEO("VIDEO", "Video"),
  
  ;
  
  private final String key;
  private final String value;
  
  ContactMethod(String key, String value) {
	  this.key = key;
	  this.value = value;	  
  }

  public String getKey() {
	return key;
  }

  public String getValue() {
	return value;
  }
  
}
