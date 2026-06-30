package com.restapidemo.restdemo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.restapidemo.restdemo.Models.BL.ProductBL;
import com.restapidemo.restdemo.Models.DL.ServiceImpl.JWTService;
import com.restapidemo.restdemo.Models.DL.ServiceImpl.LoginUserdtailsservice;
import com.restapidemo.restdemo.filter.JwtAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class AppConfig {
	private final JWTService jwtService;
	@Autowired
	JwtauthenticationEntryPoint  authenticationEntryPoint;
	@Autowired
	CustomAccessDeniedHandler customAccessDeniedHandler;
	public AppConfig(JWTService jwtService)
	{
		super();
		this.jwtService=jwtService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.cors(cors -> {})     
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/auth/**").permitAll()
						.requestMatchers("/product/**").authenticated()
						
						
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling(ex -> ex
	                    .authenticationEntryPoint(authenticationEntryPoint)
	                    .accessDeniedHandler(customAccessDeniedHandler))
				
				
				.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration config = new CorsConfiguration();

	    config.setAllowedOrigins(List.of("http://localhost:5173"));
	    config.setAllowedMethods(
	        List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
	    );
	    config.setAllowedHeaders(List.of("*"));
	    config.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source =
	            new UrlBasedCorsConfigurationSource();

	    source.registerCorsConfiguration("/**", config);

	    return source;
	}
	

	
	@Bean 
	public JwtAuthenticationFilter jwtAuthenticationFilter()
	{
		return new JwtAuthenticationFilter(userDetailsService(),jwtService);
	}
	@Bean 
	public UserDetailsService userDetailsService() 
	{
		System.out.println("UserDetailServices");		
		return new LoginUserdtailsservice();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() 
	{
		System.out.println("Authentication Provider");
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
	{
		System.out.println("AuthenritcationManager");
		return config.getAuthenticationManager();
	}
	@Bean
	public ProductBL productBL() {
		return new ProductBL();
	}

	
	
	
	
	}
