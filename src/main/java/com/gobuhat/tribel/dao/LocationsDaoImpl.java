package com.gobuhat.tribel.dao;

import java.sql.Timestamp;
import java.util.Date;
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
		TypedQuery<Locations> query = em.createQuery("SELECT l FROM Locations l ORDER BY l.startTime DESC", Locations.class);
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
	public List<Locations> findByAddress(String address, String num,
			boolean active) {
		TypedQuery<Locations> query;

		if (num != null) {
			query = active ? em
					.createQuery(
							"SELECT l FROM Locations l WHERE l.address = ?1 AND l.addNumber = ?2 AND l.active = 1 ORDER BY l.startTime DESC",
							Locations.class)
					: em.createQuery(
							"SELECT l FROM Locations l WHERE l.address = ?1 AND l.addNumber = ?2 ORDER BY l.startTime DESC",
							Locations.class);

			query.setParameter(2, num);
		} else {
			query = active ? em
					.createQuery(
							"SELECT l FROM Locations l WHERE l.address = ?1 AND l.active = 1 ORDER BY l.startTime DESC",
							Locations.class)
					: em.createQuery(
							"SELECT l FROM Locations l WHERE l.address = ?1 ORDER BY l.startTime DESC",
							Locations.class);

		}
		query.setParameter(1, address);

		return query.getResultList();
	}

	@Override
	public Locations findById(int id) {
		Locations tmp = em.find(Locations.class, id);
		return tmp;
	}

	@Override
	public List<Locations> findAllActiveLocations() {
		TypedQuery<Locations> query = em.createQuery("SELECT l FROM Locations l WHERE ?1 < l.endTime", Locations.class);
		query.setParameter(1, new Timestamp(new Date().getTime()));
		return query.getResultList();
	}	

}
