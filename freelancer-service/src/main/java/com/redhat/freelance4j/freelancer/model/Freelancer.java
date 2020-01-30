package com.redhat.freelance4j.freelancer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "FREELANCER", uniqueConstraints = @UniqueConstraint(columnNames = "freelancerId"))
public class Freelancer implements Serializable{

	private static final long serialVersionUID = 7747605823856519958L;
	
	@Id
	@Column(name="freelancerid")
	private String freelancerId;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="skills")
	private String skills;
	
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getFreelancerId() {
		return freelancerId;
	}
	public void setFreelancerId(String freelancerId) {
		this.freelancerId = freelancerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	

}
