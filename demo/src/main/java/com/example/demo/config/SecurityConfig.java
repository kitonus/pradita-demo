package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwdEnc = new BCryptPasswordEncoder(10);

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
        auth
          .inMemoryAuthentication().passwordEncoder(passwdEnc)
          .withUser("user")
            .password(passwdEnc.encode("password"))
            .roles("USER")
            .and()
          .withUser("admin")
            .password(passwdEnc.encode("admin"))
            .roles("USER", "ADMIN");
    }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().and().httpBasic().and()
        .authorizeRequests()
        .anyRequest()
        .authenticated().and()
        .csrf().disable();
	}

	
}
