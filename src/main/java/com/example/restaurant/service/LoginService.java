package com.example.restaurant.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.restaurant.entity.UsersEntity;
import com.example.restaurant.repository.ManagerRepository;

@Service
public class LoginService implements UserDetailsService {// ユーザー情報を検索
	
	@Autowired
	ManagerRepository repository;
	
	@Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
      if (StringUtils.isEmpty(username)) {
          throw new UsernameNotFoundException("ユーザー名を入力してください");
      }

      UserDetails account = repository.findByUsername(username);
      if (account == null) {
          throw new UsernameNotFoundException("ユーザー名が違います");
      }

      return account;
  }

	

}
