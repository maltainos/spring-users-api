package com.tccfinal.app.ws.shared.dto;

import java.util.List;

public class UserDto {

	private Long id;
	private String userId;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String encryptedPassword;
	private String emailVerificationToken;
	private boolean emailVerificationStatus;
	private List<AddressDTO> addresses;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
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
	

	public List<AddressDTO> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", userId=" + userId + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", password=" + password + ", encryptedPassword=" + encryptedPassword
				+ ", emailVerificationToken=" + emailVerificationToken + ", emailVerificationStatus="
				+ emailVerificationStatus + ", addressesDTO=" + addresses + "]";
	}
}
