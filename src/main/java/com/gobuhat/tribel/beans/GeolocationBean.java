package com.gobuhat.tribel.beans;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;


@Named
@Scope("session")
public class GeolocationBean {

	private String ip;
	
	public GeolocationBean() {
	}
	
	@PostConstruct
	public void init() {
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	
}
