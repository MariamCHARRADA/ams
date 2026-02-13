package com.sip.ams.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Provider {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String address;
	public int getId() {
		return id;
	}
	
	public Provider(String name, String email, String address) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
	}
	public Provider() {
		super();
	}

	@Override
	public String toString() {
		return "Provider [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + "]";
	}

	public void setId(int id) {
		this.id = id;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
