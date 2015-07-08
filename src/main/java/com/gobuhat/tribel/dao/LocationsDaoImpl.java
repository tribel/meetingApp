package com.gobuhat.tribel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.gobuhat.tribel.entity.Locations;


@Repository
public class LocationsDaoImpl implements LocationsDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Locations> findAllLocations() {
		TypedQuery<Locations> query = em.createQuery("SELECT l FROM Locations l", Locations.class);
		return query.getResultList();
	}

	@Override
	public void addLocation(Locations loc) {
		em.persist(loc);
	}


	@Override
	public void setActive(int id) {
		Locations loc = em.find(Locations.class, id);
		if (loc != null) {
			loc.setActive((byte)1);
		}
	}

	@Override
	public void setDeactive(int id) {
		Locations loc = em.find(Locations.class, id);
		if( loc != null) {
			loc.setActive((byte)0);
		}
		
	}

	@Override
	public void editLocation(Locations loc) {
		em.merge(loc);
	}

	@Override
	public List<Locations> findByAddress(String address , String num) {
		TypedQuery<Locations> query;
		
		if ( num != null) {
			 query = em.createQuery("SELECT l FROM Locations l WHERE l.address = ?1 AND l.addNumber = ?2" , Locations.class);
			 query.setParameter(2, num);
		} else {
			 query = em.createQuery("SELECT l FROM Locations l WHERE l.address = ?1" , Locations.class);
			 
		}
		query.setParameter(1, address);
		
		return query.getResultList();
	}

	@Override
	public Locations findById(int id) {
		Locations tmp = em.find(Locations.class, id);
		return tmp;
	}	
	
}
