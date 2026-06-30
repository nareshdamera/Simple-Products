package com.restapidemo.restdemo.Models.Repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restapidemo.restdemo.Models.POJO.Loginusers;


@Repository
public interface LoginusersRepository extends JpaRepository<Loginusers, Integer>{

	@Query("select l from Loginusers l where l.email=?1")
	Optional<Loginusers> getUserbyEmail(String email);
	//Loginusers findByEmail(String email);
}
