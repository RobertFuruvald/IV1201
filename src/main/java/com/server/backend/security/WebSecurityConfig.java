package com.server.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class for JWT-based web security.
 * <p>
 * Configures endpoints access rules, CSRF protection, session management,
 * and adds custom JWT authentication filter.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	/**
	 * Configures HTTP security settings for the application.
	 * <p>
	 * Disables CSRF for stateless operation, configures endpoint access,
	 * enforces stateless session management, and registers the JWT authorization
	 * filter.
	 * </p>
	 *
	 * @param http HttpSecurity to configure
	 * @return Configured HttpSecurity object
	 * @throws Exception if an error occurs during configuration
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("/api/auth/**").permitAll()
						.anyRequest().authenticated())
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	/*
	 * Authenticationmanager using plaintext password encoder for testing purposes
	 */
	@Bean
	AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return new ProviderManager(provider);
	}

	/*
	 * Authenticationmanager using passwordencoder. Will use Bcrypt if nothing else
	 * is specified
	 */

	/**
	 * Configures the {@link AuthenticationManager} with a custom authentication
	 * provider.
	 * <p>
	 * This method sets up an {@link AuthenticationManager} that uses a
	 * {@link DaoAuthenticationProvider}
	 * configured with the provided {@link UserDetailsService} and
	 * {@link PasswordEncoder}.
	 * This configuration ensures that the user details service and password encoder
	 * are used
	 * for authenticating users against the credentials stored in the application's
	 * data source.
	 * </p>
	 *
	 * @param userDetailsService the user details service that loads user-specific
	 *                           data.
	 * @param passwordEncoder    the encoder used for hashing and verifying
	 *                           passwords.
	 * @return an {@link AuthenticationManager} instance configured with a DAO
	 *         authentication provider.
	 */
/* 
	@Bean
	AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return new ProviderManager(provider);
	}
*/
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	JwtAuthorizationFilter jwtAuthorizationFilter() {
		return new JwtAuthorizationFilter();
	}

}