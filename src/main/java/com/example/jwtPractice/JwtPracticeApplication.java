package com.example.jwtPractice;

import com.example.jwtPractice.Services.UserService;
import com.example.jwtPractice.Security.UserDetailsServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories
public class JwtPracticeApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(JwtPracticeApplication.class, args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
