package com.example.restaurant.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import com.example.restaurant.entity.ReservationsEntity;

public interface ReservationsJpa extends JpaRepository<ReservationsEntity, Integer>,
									JpaSpecificationExecutor<ReservationsEntity>{
	
}
