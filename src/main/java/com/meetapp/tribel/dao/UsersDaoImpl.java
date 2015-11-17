package com.meetapp.tribel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.meetapp.tribel.entity.Users;


@Repository
public class UsersDaoImpl implements UsersDao{
	
	@PersistenceContext
	private EntityManager em;
			
	
	@Override
	public List<Users> findAllUsers() {
		TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u", Users.class);
		return query.getResultList();
	}

	@Override
	public Users autorize(Users user) {
		TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.login = :login AND u.password = :password",Users.class);
		query.setParameter("password", user.getPassword());
		query.setParameter("login", user.getLogin());
		return query.getSingleResult();
	}

	@Override
	public void save(Users user) {
		em.persist(user);
	}

	@Override
	public void edit(Users user) {	
		em.merge(user);
	}

	@Override
	public Users findById(int id) {
		Users tmpUser  = em.find(Users.class, id);
		return tmpUser;
	}
	
	
	
}
