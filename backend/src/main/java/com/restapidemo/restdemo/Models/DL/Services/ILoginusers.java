package com.restapidemo.restdemo.Models.DL.Services;

import java.util.List;
import java.util.Optional;

import com.restapidemo.restdemo.Models.POJO.Loginusers;


public interface ILoginusers {
String CreateUser(Loginusers L);
List<Loginusers>getUsers();
Optional<Loginusers>getByEmail(String email);	
}
