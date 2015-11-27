package com.gobuhat.tribel.dao;

import java.util.List;

import com.gobuhat.tribel.entity.Locations;

public interface LocationsDao {

	public List<Locations> findAllLocations();

	public void addLocation(Locations loc);

	public void setActive(int id);

	public void setDeactive(int id);

	public void editLocation(Locations loc);

	public List<Locations> findByAddress(String address, String num);
	
	public Locations findById(int id);
}
