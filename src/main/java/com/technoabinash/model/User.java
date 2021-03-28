package com.technoabinash.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "UserName can not be empty!!")
	@Size(min = 5,max = 15,message = "Name must be between 5 to 15 characters!!")
	private String name;
	
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid email")
	@Column(unique = true)
	private String email;
	private String password;
	
	@Column(length = 500)
	private String about;
	private String role;
	private boolean activity;
	private String imageUrl;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY ,mappedBy = "user")
	private List<Contacts> contact=new ArrayList<Contacts>();
	
	
	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", "
				+ "email=" + email + ", password=" + password + ", "
				+ "about=" + about+ ", role=" + role + ", "
				+ "activity=" + activity + ", imageUrl=" + imageUrl + ", contact=" + contact + "]";
	}
	public User(String name, String email, String password, String about, String role, boolean activity,
			String imageUrl, List<Contacts> contact) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.role = role;
		this.activity = activity;
		this.imageUrl = imageUrl;
		this.contact = contact;
	}
	public User(int id, String name, String email, String password, String about, String role, boolean activity,
			String imageUrl, List<Contacts> contact) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.role = role;
		this.activity = activity;
		this.imageUrl = imageUrl;
		this.contact = contact;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isActivity() {
		return activity;
	}
	public void setActivity(boolean activity) {
		this.activity = activity;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public List<Contacts> getContact() {
		return contact;
	}
	public void setContact(List<Contacts> contact) {
		this.contact = contact;
	}
	
	
}
