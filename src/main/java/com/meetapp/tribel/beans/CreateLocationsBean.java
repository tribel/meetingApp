package com.meetapp.tribel.beans;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.meetapp.tribel.entity.Users;
import com.meetapp.tribel.service.LocationsService;


@Named
@Scope("request")
public class CreateLocationsBean {

	@Inject
	private LocationsService locationsService;
	
	private String telephone;
	private Date startDate;
	private Date endDate;
	private String description;
	private String wishes;
	private String address;
	private double lat;
	private double lng;
	
	public CreateLocationsBean() {
	
	}
	
	@PostConstruct
	public void postInit() {	
	}

	public LocationsService getLocationsService() {
		return locationsService;
	}

	public void setLocationsService(LocationsService locationsService) {
		this.locationsService = locationsService;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	
	public void saveLocation() {
		
	}
	
	public void saveLocationNonAutorized() {
		Users nonAutUser = new Users();
		nonAutUser.setName("");
	}

}
