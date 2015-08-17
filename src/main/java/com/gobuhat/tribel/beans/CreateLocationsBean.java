package com.gobuhat.tribel.beans;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.gobuhat.tribel.service.LocationsService;


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
	
	public CreateLocationsBean() {
	
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
	
}
