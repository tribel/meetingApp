package com.gobuhat.tribel.beans;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.map.*;
import org.springframework.context.annotation.Scope; 

import com.gobuhat.tribel.entity.Locations;
import com.gobuhat.tribel.service.LocationsService;

@Named
@Scope("session")
public class FindLocationsBeans {

	@Inject
	private LocationsService locationsService;

	private List<Locations> locList;
	private Locations location;

	private String tableMapFlag;
	private boolean booltableMapFlag;

	private String filterAddress = new String();
	private Date filterDate;
	
	private MapModel mapModel = new DefaultMapModel();
	

	public FindLocationsBeans() {
		//locList = locationsService.findAllLocations();
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

	public String getFilterAddress() {
		return filterAddress;
	}

	public void setFilterAddress(String filterAddress) {
		this.filterAddress = filterAddress;
	}

	public java.util.Date getFilterDate() {
		return filterDate;
	}

	public void setFilterDate(java.util.Date filterDate) {
		this.filterDate = filterDate;
	}

	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}

	/*
	 * End of set-get methods
	 */

	
	public void onTableMapChange() {
		if (tableMapFlag.equals("0")) {
			booltableMapFlag = true;
		} else {
			booltableMapFlag = false;
		}

	}

	public void findAllLocatoins() {
		locList = locationsService.findAllLocations();

	}

	public void findFilteredLocation() {
		if ( filterAddress.equals("") && filterDate == null) {
			locList = locationsService.findAllLocations();
			mapViewController();
			return;
		}
		
		if ( !filterAddress.equals("")) {
			locList = locationsService.findByAddress(filterAddress, null);
		} else {
			locList = locationsService.findAllLocations();
		}

		if (filterDate != null) {
			Iterator<Locations> itr = locList.iterator();
			Instant instant = Instant.ofEpochMilli(filterDate.getTime());
			LocalDate tmpFilterDate = LocalDateTime.ofInstant(instant,
					ZoneId.systemDefault()).toLocalDate();

			while (itr.hasNext()) {
				Locations tmpLocat = itr.next();
				if (!tmpLocat.getStartTime().toLocalDateTime().toLocalDate().equals(tmpFilterDate)
						&& !tmpLocat.getEndTime().toLocalDateTime().toLocalDate().equals(tmpFilterDate)) {

					itr.remove();
				}
			}
		}
		
		mapViewController();
	}
	
	
	public void mapViewController() {
		mapModel = new DefaultMapModel();
		
		for (Locations l : locList) {
			mapModel.addOverlay(new Marker(new LatLng(l.getLat(), l.getLng()), l.getUser().getName()));	
		}
		
	}
	
	public Collection<String> completeAddress(String query) {
		List<String> results = new ArrayList<>();
		List<Locations> list = locationsService.findAllLocations();
		for( int i = 0; i < list.size(); i++ ) {
			if (list.get(i).getAddress().startsWith(query)) {
				results.add(list.get(i).getAddress());
			}	
		}
		Set<String> set = new HashSet<String>(results);
		results.clear();
		results.addAll(set);
		
		return results;
	}

}
