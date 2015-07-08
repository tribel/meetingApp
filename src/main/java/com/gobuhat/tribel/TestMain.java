package com.gobuhat.tribel;

import java.sql.Timestamp;
//import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gobuhat.tribel.entity.Locations;
import com.gobuhat.tribel.entity.Users;
import com.gobuhat.tribel.service.LocationsService;
//import com.gobuhat.tribel.service.UsersService;

public class TestMain {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {	
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		//UsersService service = context.getBean(UsersService.class);
		LocationsService locationsService = context.getBean(LocationsService.class);
	//	LocalDate cld = LocalDate.of(1994, Month.JANUARY, 6);
		
		Users myUser = new Users();

		myUser.setName("Non Login");
		myUser.setSex("male");
		myUser.setTelephone("0631686371");

		
		byte[] bs = { 1 , 3, 3, 2, 2,3 ,3 ,3};
		myUser.setImage(bs);
		//service.save(myUser);
		
		
		/*List<Users> list = service.findAllUsers();
		for(Users u: list) {
			
			System.out.println(u);
		}
		*/
	/*	Users editUs  = service.findById(101);
		editUs.setSex("male");
		service.edit(editUs);*/
		
		LocalDateTime dateTime = LocalDateTime.now(); 
		Locations locations = new Locations();
		locations.setActive((byte)1);
		locations.setAddNumber("22");
		locations.setAddress("sevastopolska");
		locations.setAmount(3);
		locations.setDescription("buhach");
		locations.setEndTime(Timestamp.valueOf(dateTime.plusHours(5)));
		locations.setLat(60.23);
		locations.setLng(12.23);
		locations.setPublishTime(Timestamp.valueOf(dateTime));
		locations.setStartTime(Timestamp.valueOf(dateTime));
		locations.setUser(myUser);
		locations.setWishes("bring food");
		
		//locationsService.addLocation(locations, service.findById(101));
		
		/*Locations editLoc  = locationsService.findById(251);
		editLoc.setActive((byte)0);
		locationsService.editLocation(editLoc);*/
		
		List<Locations> list = locationsService.findAllLocations();
		for(Locations locations2 : list) {
			System.out.println(locations2);
		}
			
		/*List<Locations> locations2 = locationsService.findAllLocations();
		for(Locations l: locations2) {
			System.out.println(l);
		}*/
		
		//locationsService.addLocationByNonAutorize(locations, myUser);
	}
	
}
