package com.tccfinal.app.ws.io.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users", schema = "monographis")
public class UserEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 35, nullable = false)
	private String userId;
	
	@Column(length = 255, nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String encryptedPassword;
	
	@Column(length = 50, nullable = false)
	private String firstName;
	
	@Column(length = 25, nullable = false)
	private String lastName;

	private String emailVerificationToken;
	
	@Column(columnDefinition = "boolean default false")
	private boolean emailVerificationStatus;
	
	@OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
	private List<AddressEntity> addresses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
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

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", userId=" + userId + ", email=" + email + ", encryptedPassword="
				+ encryptedPassword + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailVerificationToken=" + emailVerificationToken + ", emailVerificationStatus="
				+ emailVerificationStatus +"]";
	}
}















