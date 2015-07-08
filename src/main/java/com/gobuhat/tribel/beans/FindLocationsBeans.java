package com.gobuhat.tribel.beans;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.gobuhat.tribel.entity.Locations;
import com.gobuhat.tribel.service.LocationsService;

@Named
@Scope("request")
public class FindLocationsBeans {
	
	@Inject
	private LocationsService locationsService;

	private List<Locations> locList;
	private Locations location;
	
	public FindLocationsBeans() {
	}

	public LocationsService getLocationsService() {
		return locationsService;
	}

	public void setLocationsService(LocationsService locationsService) {
		this.locationsService = locationsService;
	}

	public List<Locations> getLocList() {
		return locList;
	}

	public void setLocList(List<Locations> locList) {
		this.locList = locList;
	}

	public Locations getLocation() {
		return location;
	}

	public void setLocation(Locations location) {
		this.location = location;
	}
	
	
}
