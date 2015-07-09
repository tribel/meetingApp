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
	
	private String  tableMapFlag ;
	private boolean booltableMapFlag = true;
	
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

	public String getTableMapFlag() {
		return tableMapFlag;
	}

	public void setTableMapFlag(String tableMapFlag) {
		this.tableMapFlag = tableMapFlag;
	}

	public boolean isBooltableMapFlag() {
		return booltableMapFlag;
	}

	public void setBooltableMapFlag(boolean booltableMapFlag) {
		this.booltableMapFlag = booltableMapFlag;
	}

	
	/*
	 * End of set-get methods
	 */
	
	
	public void onTableMapChange() {
		System.out.println("FlaG =" + tableMapFlag);
		if ( tableMapFlag.equals("0")) {
			booltableMapFlag = true;
		} else {
			booltableMapFlag = false;
		}
		System.out.println(booltableMapFlag);
		
	}
	
	public List<Locations> findAllLocatoins() {
		locList = locationsService.findAllLocations();
		return locList;
	}
	
	
}
