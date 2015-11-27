package com.gobuhat.tribel.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Locations {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Timestamp publishTime;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Users user;	
	
	private int amount;
	private Timestamp startTime;
	private Timestamp endTime;
	private String description;
	private String wishes;
	private double lat;
	private double lng;
	private byte active;
	private String address;
	private String addNumber;
	
	public Locations() {
	}
	
	public Locations(int id, Timestamp publishTime, Users user, int amount,
			Timestamp startTime, Timestamp endTime, String description,
			String wishes, double lat, double lng , String address, String addNumber) {

		this.id = id;
		this.publishTime = publishTime;
		this.user = user;
		this.amount = amount;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.wishes = wishes;
		this.lat = lat;
		this.lng = lng;
		this.address = address;
		this.addNumber = addNumber;
		this.active = 1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWishes() {
		return wishes;
	}

	public void setWishes(String wishes) {
		this.wishes = wishes;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public byte getActive() {
		return active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddNumber() {
		return addNumber;
	}

	public void setAddNumber(String addNumber) {
		this.addNumber = addNumber;
	}

	@Override
	public String toString() {
		return "Locations [id=" + id + ", publishTime=" + publishTime
				+ ", user=" + user + ", amount=" + amount + ", startTime="
				+ startTime + ", endTime=" + endTime + ", description="
				+ description + ", wishes=" + wishes + ", lat=" + lat
				+ ", lng=" + lng + ", active=" + active + ", address="
				+ address + ", addNumber=" + addNumber + "]";
	}


	
	
	
}
