package com.example.restaurant.jpa;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.restaurant.entity.UsersEntity;



public interface UsersInterface extends JpaRepository<UsersEntity, String>{
	public UserDetails findByUsername(String username);

	
}
