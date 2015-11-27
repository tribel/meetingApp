package com.gobuhat.tribel.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gobuhat.tribel.dao.UsersDao;
import com.gobuhat.tribel.entity.Users;

@Named
public class UsersServiceImpl implements UsersService{
	
	@Inject
	private UsersDao usersDao;
	
	@Override
	public List<Users> findAllUsers() {
		return usersDao.findAllUsers();
	}

	@Override
	public Users autorize(Users user) {		
		return usersDao.autorize(user);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void save(Users user) {
		usersDao.save(user);
	}

	@Override
	@Transactional
	public void edit(Users user) {
		usersDao.edit(user);
	}
	
	@Override
	public Users findById(int id) {
		return usersDao.findById(id);
	}
	
	
}
