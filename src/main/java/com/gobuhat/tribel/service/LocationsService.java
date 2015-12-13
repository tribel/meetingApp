package com.gobuhat.tribel.service;

import java.util.List;

import com.gobuhat.tribel.entity.Locations;
import com.gobuhat.tribel.entity.Users;

public interface LocationsService {

	public List<Locations> findAllLocations();

	public void addLocation(Locations loc, Users user);
	
	public void addLocationByNonAutorize(Locations locations , Users users);

	public void setActive(int id);

	public void setDeactive(int id);

	public void editLocation(Locations loc);
	
	public List<Locations> findByAddress(String address ,String num, boolean active);
	
	public Locations findById(int id);
	
	public List<Locations> findAllActiveLocations();

}
