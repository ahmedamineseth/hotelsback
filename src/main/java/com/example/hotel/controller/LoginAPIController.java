package com.example.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.hotel.entities.AdminEntity;
import com.example.hotel.service.AdminService;

import java.util.Base64;

@RestController()
@RequestMapping("/api/login")
public class LoginAPIController {

	@Autowired
	AdminService as; 
	
	private static BCryptPasswordEncoder passwordEcorder = new BCryptPasswordEncoder();
	
	@PostMapping(path = "", produces = "application/json")
    ResponseEntity<AdminEntity> checkLogin( @RequestBody AdminEntity userv ){
		
		System.out.println( passwordEcorder.encode( "m2iFinFormation" ) );  

		try {
			AdminEntity ae = as.findByUsername( userv.getUsername() );
			
			if( ae != null && passwordEcorder.matches( userv.getPassword() , ae.getPassword() ) ) {
				String encoding = Base64.getEncoder().encodeToString((userv.getUsername()+":"+userv.getPassword()).getBytes());
				 
				ae.setPassword(encoding); 
				return ResponseEntity.ok() // ok => 200 
	                    .body(ae);	
			}else {
				throw new Exception("Login ou mot de passe incorrect"); 
			}
			
		}catch( Exception e ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() ); 
		}
	}
	
}
