package com.restapidemo.restdemo.Models.DL.ServiceImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.restapidemo.restdemo.Models.POJO.Loginusers;



public class LoginUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String  password;
	private List<GrantedAuthority>role;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.role;
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	public LoginUserDetails(Loginusers users)
	{
		//ROLE_USER      hasRole("USER")--- Correct
		//USER			hasRole("USER")  -- INCORRECT
		//USER          hasAuthority("USER")   correct
		
		this.email=users.getEmail();
		this.password=users.getPassword();
		this.role= Arrays.stream(users.getRole().split(",")).map(r->new SimpleGrantedAuthority(r.toUpperCase())).collect(Collectors.toList());                    //Manger,admin,user

		
	}
	
	

}
