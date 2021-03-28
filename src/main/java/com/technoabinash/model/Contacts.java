package com.technoabinash.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Contacts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	private String name;
	private String lastName;
	private String email;
	private String phone;
	
	@Column(length = 500)
	private String description;
	private String work;
	private String image;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	
	
	
	@Override
	public String toString() {
		return "Contacts [cId=" + cId + ", name=" + name + ", lastName=" + lastName + ", email=" + email + ", phone="
				+ phone + ", description=" + description + ", work=" + work + ", image=" + image + ", user=" + user
				+ "]";
	}
	public Contacts(int cId, String name, String lastName, String email, String phone, String description, String work,
			String image, User user) {
		super();
		this.cId = cId;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.description = description;
		this.work = work;
		this.image = image;
		this.user = user;
	}
	public Contacts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
