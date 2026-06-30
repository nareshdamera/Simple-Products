package com.restapidemo.restdemo.Models.DL.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.restapidemo.restdemo.Models.DL.Services.ILoginusers;
import com.restapidemo.restdemo.Models.POJO.Loginusers;
import com.restapidemo.restdemo.Models.Repositories.LoginusersRepository;

@Service
public class LoginusersServiceImpl implements ILoginusers {
@Autowired
PasswordEncoder passwordEncoder;
	@Autowired
	LoginusersRepository loginusersRepository;

	@Override
	public String CreateUser(Loginusers L) {
		Optional<Loginusers> user = loginusersRepository.getUserbyEmail(L.getEmail());
		if (user.isPresent())
			return "User exists";
		else {// TODO Auto-generated method stub
			L.setPassword(passwordEncoder.encode(L.getPassword()));
			loginusersRepository.save(L);
			return "User created";
		}
	}

	@Override
	public List<Loginusers> getUsers() {
		// TODO Auto-generated method stub
		return loginusersRepository.findAll();
	}

	@Override
	public Optional<Loginusers> getByEmail(String email) {
		// TODO Auto-generated method stub
		return loginusersRepository.getUserbyEmail(email);
	}

}
