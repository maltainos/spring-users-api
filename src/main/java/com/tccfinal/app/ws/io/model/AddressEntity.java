package com.tccfinal.app.ws.io.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name = "addresses", schema = "monographis")
public class AddressEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30, unique = true)
	private String addressId;
	
	@Column(nullable = false, length = 30)
	private String city;
	
	@Column(nullable = false, length = 30)
	private String country;
	
	@Column(nullable = false, length = 30)
	private String streetName;
	
	@Column(nullable = false, length = 30)
	private String postalCode;
	
	@Column(nullable = false, length = 30)
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity userDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserEntity getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public String toString() {
		return "AddressEntity [id=" + id + ", addressId=" + addressId + ", city=" + city + ", country=" + country
				+ ", streetName=" + streetName + ", postalCode=" + postalCode + ", type=" + type + ", userDetails="
				+ userDetails + "]";
	}
	
}
