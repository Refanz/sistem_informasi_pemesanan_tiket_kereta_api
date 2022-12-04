package com.refanzzzz.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/pesan_tiket", "/about", "/order_form/{id_tiket}", "/payment_form",
					"/payment_success", "/daftar_jadwal", "/tambah_jadwal",
					"/proses_tambah_jadwal", "/edit_jadwal/{id_jadwal}",
					"/proses_edit_jadwal", "/proses_hapus_jadwal/{id_jadwal}", "/pulihkan_jadwal",
					"/daftar_kereta", "/tambah_kereta", "/proses_tambah_kereta",
					"/edit_kereta/{id_kereta}", "/proses_edit_kereta", "/proses_hapus_kereta/{id_kereta}",
					"/pulihkan_kereta", "/pulihkan_kereta/recover/{id_kereta}",
					"/pulihkan_kereta/delete/{id_kereta}").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("username")
				.defaultSuccessUrl("/")
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/login").permitAll();
	}
	
	
}
