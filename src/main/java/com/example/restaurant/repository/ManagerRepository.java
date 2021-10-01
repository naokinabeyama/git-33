package com.example.restaurant.repository;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.restaurant.jpa.UsersInterface;
import com.example.restaurant.entity.ReservationsEntity;
import com.example.restaurant.entity.UsersEntity;
import com.example.restaurant.jpa.ReservationsJpa;

@Repository
public class ManagerRepository {
	
	@Autowired
	UsersInterface usersInterface;
	
	@Autowired
	ReservationsJpa reservationsJpa;
	
	
	public Page<ReservationsEntity> getPage(Specification<ReservationsEntity> searchConditions, Pageable pageable){
    Page<ReservationsEntity> entityPageList  = reservationsJpa.findAll(searchConditions, pageable);
    return entityPageList;
	}
	
	public List<ReservationsEntity> selectAll() {
		List<ReservationsEntity> reservationsEntityList = reservationsJpa.findAll();
		return reservationsEntityList;
	}
	
	public ReservationsEntity getRecord(int id) {
		ReservationsEntity reservationsEntity = reservationsJpa.getOne(id);
		return reservationsEntity;
	}
	
	public void updateDB(ReservationsEntity entity) {
		reservationsJpa.save(entity);
	}
	
	public void deleteDB(int reservationId) {
		reservationsJpa.deleteById(reservationId);
	}
	
	public UserDetails findByUsername(String username) {
    UserDetails usersEntity = usersInterface.findByUsername(username);
        return usersEntity ;
  }

	
}
