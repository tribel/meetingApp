package com.gobuhat.tribel.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.context.annotation.Scope;

import com.gobuhat.tribel.entity.Locations;
import com.gobuhat.tribel.entity.Users;
import com.gobuhat.tribel.service.LocationsService;


@Named
@ViewScoped
public class CreateLocationsBean {

	@Inject
	private LocationsService locationsService;
	
	
	private String centerGeoMap = "41.850033, -87.6500523";
	private Users nonAutorizedUser = new Users();
	private MapModel geoModel = new DefaultMapModel();
	private Locations creatingLocation;
	private List<String> geoAddressList = new ArrayList<>();
	
	private Date startDate;
	private Date endDate;
	private String description;
	private String wishes;
	private String address = new String();
	private double lat;
	private double lng;
	
	public CreateLocationsBean() {
	
	}
	
	public String getCenterGeoMap() {
		return centerGeoMap;
	}

	public void setCenterGeoMap(String centerGeoMap) {
		this.centerGeoMap = centerGeoMap;
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

	public Users getNonAutorizedUser() {
		return nonAutorizedUser;
	}

	public void setNonAutorizedUser(Users nonAutorizedUser) {
		this.nonAutorizedUser = nonAutorizedUser;
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
	
	public MapModel getGeoModel() {
		return geoModel;
	}

	public void setGeoModel(MapModel geoModel) {
		this.geoModel = geoModel;
	}

	private Locations sameSaveLocation() {
		creatingLocation.setPublishTime(new Timestamp(new Date().getTime()));
		creatingLocation.setStartTime(new Timestamp(startDate.getTime()));
		creatingLocation.setEndTime(new Timestamp(endDate.getTime()));
		creatingLocation.setDescription(description);
		creatingLocation.setWishes(wishes);
		creatingLocation.setAddress(address);
		creatingLocation.setLat(lat);
		creatingLocation.setLng(lng);
		creatingLocation.setActive((byte)1);
		
		return creatingLocation;
	}
	
	public void saveLocation(String login, String password) {
		Users tmpUser = new Users(login, password);
		locationsService.addLocation( sameSaveLocation() , tmpUser);
	}
	
	public void saveLocationNonAutorized() {
		locationsService.addLocationByNonAutorize( sameSaveLocation() , nonAutorizedUser);
	}
	
	public void onGeocode(GeocodeEvent event) {
		 List<GeocodeResult> results = event.getResults();
		 
	        if (results != null && !results.isEmpty()) {
	        	System.out.println("VICkochu");
	            LatLng center = results.get(0).getLatLng();
	            centerGeoMap = center.getLat() + "," + center.getLng();
	            geoAddressList = new ArrayList<>();
	            
	            for (GeocodeResult r: results) {
	                geoModel.addOverlay(new Marker(r.getLatLng(), r.getAddress()));
	                geoAddressList.add(r.getAddress());
	            }
	        }
	        
	        System.out.println("Posmotri = " + geoAddressList);
	}
	
	public Collection<String> completeAddress(String query) {
		System.out.println("��������� ������ -- "  + address);
		System.out.println("-------" + geoAddressList + "--------");
		//address = "���� ���������";
		return geoAddressList;
	}

}
