package com.restapidemo.restdemo.Models.DL.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.restapidemo.restdemo.Models.POJO.Loginusers;
import com.restapidemo.restdemo.Models.Repositories.LoginusersRepository;


public class LoginUserdtailsservice implements UserDetailsService {

	@Autowired
	LoginusersRepository loginusersRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Loginusers> OP=loginusersRepository.getUserbyEmail(username);
		
		return OP.map(LoginUserDetails::new).orElseThrow(()->new UsernameNotFoundException("Username not found"));                               //new LoginUserDetail()
	}

}
