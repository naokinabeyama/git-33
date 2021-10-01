package com.example.restaurant.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.restaurant.entity.ReservationsEntity;
import com.example.restaurant.jpa.ReservationsJpa;



@Repository
public class CustomerRepository {
	
	@Autowired
	ReservationsJpa reservationsjpa;
	
	public void reservationDB(ReservationsEntity entity) {
		reservationsjpa.save(entity);
	}
}
