package com.gobuhat.tribel.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import com.gobuhat.tribel.dao.LocationsDao;
import com.gobuhat.tribel.entity.Locations;
import com.gobuhat.tribel.entity.Users;

@Named
public class LocationsServiceImpl implements LocationsService{
	
	@Inject
	private LocationsDao locationsDao;
	
	@Inject
	private UsersService usersServ;

	@Override
	public List<Locations> findAllLocations() {
		return locationsDao.findAllLocations();
	}

	@Override
	@Transactional
	public void addLocation(Locations loc , Users user) {
		Users autUser = usersServ.autorize(user);
		if ( autUser != null ) {
			loc.setUser(autUser);
		}
		locationsDao.addLocation(loc);
	}


	@Override
	@Transactional
	public void addLocationByNonAutorize(Locations locations, Users users) {
		usersServ.save(users);
		locations.setUser(users);
		locationsDao.addLocation(locations);
	}
	
	@Override
	@Transactional
	public void setActive(int id) {
		locationsDao.setActive(id);
	}

	@Override
	@Transactional
	public void setDeactive(int id) {
		locationsDao.setDeactive(id);
	}

	@Override
	@Transactional
	public void editLocation(Locations loc) {
		locationsDao.editLocation(loc);
	}
	
	@Override
	public List<Locations> findByAddress(String address, String num) {
		return locationsDao.findByAddress(address, num);
	}

	@Override
	public Locations findById(int id) {
		return locationsDao.findById(id);
	}

	
}
