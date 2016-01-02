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

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	private boolean activeStatusFlag = true;

	private String filterAddress = new String();
	private Date filterDate;
	
	private MapModel mapModel = new DefaultMapModel();
	private MapModel tableMapModel = new DefaultMapModel();
	private String centerTableGeoMap = "50.27, 30.30";
	

	public FindLocationsBeans() {
	}

	@PostConstruct
	public void init() {
		this.findAllActiveLocations();
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

	public MapModel getTableMapModel() {
		return tableMapModel;
	}

	public void setTableMapModel(MapModel tableMapModel) {
		this.tableMapModel = tableMapModel;
	}

	public String getCenterTableGeoMap() {
		return centerTableGeoMap;
	}

	public void setCenterTableGeoMap(String centerTableGeoMap) {
		this.centerTableGeoMap = centerTableGeoMap;
	}

	public boolean isActiveStatusFlag() {
		return activeStatusFlag;
	}

	public void setActiveStatusFlag(boolean activeStatusFlag) {
		this.activeStatusFlag = activeStatusFlag;
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
	
	public void findAllActiveLocations() {
		locList = locationsService.findAllActiveLocations();
	}

	public void findFilteredLocation() {
		if ( filterAddress.equals("") && filterDate == null ) {
			locList = activeStatusFlag ? 
						 locationsService.findAllActiveLocations() :
						 locationsService.findAllLocations();
			mapViewController();
			return;
		}
		
		if ( !filterAddress.equals("")) {
			locList = locationsService.findByAddress(filterAddress, null, activeStatusFlag);
		} else {
			locList = locationsService.findAllLocations();
		}

		
		// data base finding!!!!
		if (filterDate != null) {
			Iterator<Locations> itr = locList.iterator();
			Instant instant = Instant.ofEpochMilli(filterDate.getTime());
			LocalDate tmpFilterDate = LocalDateTime.ofInstant(instant,
					ZoneId.systemDefault()).toLocalDate();

			while (itr.hasNext()) {
				Locations tmpLocat = itr.next();
				if (tmpLocat.getStartTime().toLocalDateTime().toLocalDate().isAfter(tmpFilterDate)
						|| tmpLocat.getEndTime().toLocalDateTime().toLocalDate().isBefore(tmpFilterDate)) {

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
		List<Locations> list = activeStatusFlag ? locationsService.findAllActiveLocations() :
												  locationsService.findAllLocations();
		
		// do data base orient. method  findAddressLike %//% . to avoid slow performance
		// String.contains
		
		for( int i = 0; i < list.size(); i++ ) {
			if(list.get(i).getAddress().toLowerCase().contains(query.toLowerCase())) {
				results.add(list.get(i).getAddress());
			}
		}
		Set<String> set = new HashSet<String>(results);
		results.clear();
		results.addAll(set);
		
		return results;
	}
	
	public void addActiveLocationsAjaxMessage() {
		String summary = activeStatusFlag ? "Искать только актуальные" : "Искать все";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
	}
	
	public void addMarkerTableValue(Locations l) {
		tableMapModel = new DefaultMapModel();
		tableMapModel.addOverlay(new Marker(new LatLng(l.getLat(), l.getLng()), l.getUser().getName()));
	}
 
}
