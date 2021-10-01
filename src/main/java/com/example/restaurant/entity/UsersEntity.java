package com.example.restaurant.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class UsersEntity implements UserDetails{
	
	@Id //primarykey
	@Column(name="id")
	private String username;
	private String password;

	
	/**
	 * @return username
	 */
	@Override
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username セットする username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	// ↓認可機能が必要な時
	
	@Override
  public Collection<? extends GrantedAuthority> getAuthorities() { //ユーザーの権限
      return null; 
  }
	
  @Override
  public boolean isAccountNonExpired() { //アカウントの有効期限
      return true;
  }
  
  @Override
  public boolean isAccountNonLocked() { //ユーザーロック
      return true;
  }

  @Override
  public boolean isCredentialsNonExpired() { //パスワードの有効期限
      return true;
  }
  
   @Override
  public boolean isEnabled() { //アカウントの有効無効
      return true;
  }


}
