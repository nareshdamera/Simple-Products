package com.restapidemo.restdemo.Controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapidemo.restdemo.Models.DL.ServiceImpl.JWTService;
import com.restapidemo.restdemo.Models.DL.ServiceImpl.LoginusersServiceImpl;
import com.restapidemo.restdemo.Models.POJO.Loginusers;
import com.restapidemo.restdemo.dto.LoginDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Jwks.OP;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	LoginusersServiceImpl loginusersServiceImpl;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JWTService jwtService;
	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/save")
	public String Save(@RequestBody Loginusers L) {
		return loginusersServiceImpl.CreateUser(L);

	}

	@PostMapping("/validate")
	public ResponseEntity<Map<String, String>> LoginValidate(@RequestBody LoginDto L) {
		Map<String,String> map=new HashMap<String, String>();
		Optional<Loginusers>Op= loginusersServiceImpl.getByEmail(L.getEmail());
		
		  if(Op.isEmpty())
		  {
			  map.put("error", "User not found");
			  return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
		  }
		  else if(passwordEncoder.matches(L.getPassword(), Op.get().getPassword()))
		  {
			  Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(L.getEmail(),L.getPassword()));
			  if(authentication.isAuthenticated())
			  {
				  map.put("role",Op.get().getRole());
				  map.put("token",jwtService.generateToken(Op.get().getEmail(), Op.get().getRole()));
				 System.out.println(authentication.getName());
				 System.out.println(authentication.getAuthorities());
				 System.out.println(authentication.getDetails());
				 System.out.println(authentication.getCredentials());
				  return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
			  }
			  
			  return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
			  
		  }
		  else
		   {
			  
			  map.put("error", "Password not correct");
			  return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
		   }
		}
}
