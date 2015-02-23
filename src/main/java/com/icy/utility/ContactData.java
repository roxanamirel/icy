package com.icy.utility;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ContactData {
	@NotEmpty(message="Mandatory field")
	private String name;
	@NotEmpty(message="Mandatory field")
	@Email(message="Invalid field")
	private String email;
	@NotEmpty(message="Mandatory field")
	private String message;
	@NotEmpty(message="Mandatory field")
    private String subject;
	
	public ContactData() {
		
	}

	public ContactData(String name, String email, String message,String subject) {
		super();
		this.name = name;
		this.email = email;
		this.message = message;
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	

}
