package com.example.restaurant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.restaurant.service.LoginService;





@EnableWebSecurity //springsecurityを有効にする
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
  LoginService loginService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
  protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
      authenticationManagerBuilder
              .userDetailsService (this.loginService) //認証するユーザーを設定をしている
              .passwordEncoder(passwordEncoder()); // 入力されたパスワードがあっているか
  }

	@Override
  protected void configure(HttpSecurity httpSecurity) throws Exception{
      
			httpSecurity
      	.csrf().disable() ////セキュリティ機能の一つ、disable()はcsrf()メソッドを使わない
      	.formLogin() //フォーム認証
	      	.loginProcessingUrl("/manage/login") //ログイン処理URL
	      	.loginPage("/manage/") //ログインページ URLでログイン画面を判断している
	      	.defaultSuccessUrl("/manage/reservationList").permitAll(); //ログイン成功した後の遷移先
			  //↑permitAll() 全てのユーザーに対してログインページへのアクセス許可
      	
			
			httpSecurity //ログアウト
				.logout()
				.permitAll(); 

      httpSecurity
       	.authorizeRequests() //認証が必要となるURLを設定する関数
	        .antMatchers("/css/**","/store/**","/img/**").permitAll() //すべてのユーザーに対しアクセスできる
	        .anyRequest().authenticated(); // 全てのURLリクエストは認証されているユーザーしかアクセスできない
  }
	
  @Bean
  public PasswordEncoder passwordEncoder() { //パスワードのハッシュ化
  	return new BCryptPasswordEncoder();
  }
}

