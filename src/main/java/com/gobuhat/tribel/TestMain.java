
package com.gobuhat.tribel;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gobuhat.tribel.entity.Locations;
import com.gobuhat.tribel.service.LocationsService;
import com.gobuhat.tribel.service.UsersService;


public class TestMain {
	
	public static void main(String[] args) {	
		
			ApplicationContext context = new ClassPathXmlApplicationContext("application-config.xml");
			UsersService usersService = context.getBean(UsersService.class);	
			LocationsService locationsService = context.getBean(LocationsService.class);

	
	
	}
}
