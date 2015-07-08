package com.gobuhat.tribel.entity;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private java.sql.Date birthday;
	private String sex;
	private String login;
	private String password;
	private String telephone;
	
	@Lob
	private byte[] image;
	
	@OneToMany(mappedBy="user" , cascade= CascadeType.PERSIST )
	private Collection<Locations> locations;
	
	public Users() {
	}

	public Users(int id, String name, Date birthday, String sex, String login,
			String password, String telephone, byte[] image) {

		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.sex = sex;
		this.login = login;
		this.password = password;
		this.telephone = telephone;
		this.image = image;
	}
	
	public Users(String login, String password) {
		this.login = login;
		this.password = password;
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

	public java.sql.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Collection<Locations> getLocations() {
		return locations;
	}

	public void setLocations(Collection<Locations> locations) {
		this.locations = locations;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", birthday=" + birthday
				+ ", sex=" + sex + ", login=" + login + ", password="
				+ password + ", telephone=" + telephone + "]";
	}
	
		
}
