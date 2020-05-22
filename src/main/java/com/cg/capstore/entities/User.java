package com.cg.capstore.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USER_MASTER")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD",length = 60)
	private String password;
	
	@Column(name="SECURITY_QUESTION")
	private String securityQuestion;
	
	@Column(name="SECURITY_ANSWER")
	private String securityAnswer;
	
	@Column(name="ROLE")
	private String role;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Address> addresses =new HashSet<Address>();
	
	public User() {
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@JsonIgnore
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	public void addAddress(Address address) {
		address.setUser(this);
		this.getAddresses().add(address);
	}
	
	public void removeAddress(Address address) {
		address.setUser(null);
		this.getAddresses().remove(address);
	}

	
	
	
}
