package com.tccfinal.app.ws.ui.model.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class UserDetailsRequestModel {

	@NotBlank(message = "Campo Email eh Obrigatorio")
	private String email;

	@NotBlank(message = "Campo Senha Eh Obrigatorio")
	private String password;

	@NotBlank(message = "Campo Primeiro Nome eh Obrigatorio")
	private String firstName;

	@NotBlank(message = "Campo Ultimo Nome eh Obrigatorio")
	private String lastName;

	private List<AddressRequestModel> addresses;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List<AddressRequestModel> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressRequestModel> addresses) {
		this.addresses = addresses;
	}
}
