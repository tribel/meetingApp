package com.gobuhat.tribel.beans;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class MessageBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final String nameRequired = "Поле имени не может быть пустым!";
	private final String telephoneRequired = "Телефон обязательное поле!";
	private final String dateRequired = "Время начала обязательно для заполнения!";
	private final String addressRequired = "Поле адреса не может быть пустым!";
	
	public String getNameRequired() {
		return nameRequired;
	}
	public String getTelephoneRequired() {
		return telephoneRequired;
	}
	public String getDateRequired() {
		return dateRequired;
	}
	public String getAddressRequired() {
		return addressRequired;
	}
	
	
}