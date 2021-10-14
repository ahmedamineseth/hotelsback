package com.example.hotel.controller;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.hotel.entities.ClientEntity;
import com.example.hotel.service.ClientService;


@RestController()
@RequestMapping("/api/client")
public class ClientAPIController {

	@Autowired
	ClientService hs; 
	
	@GetMapping( path="" , produces = "application/json" )
	public Iterable<ClientEntity> getAll(){
		return hs.getAllClient(); 
	}
	
	@GetMapping( path="/{id}" , produces = "application/json" )
	public ResponseEntity<ClientEntity> get( @PathVariable("id") int id ){
		try {
			ClientEntity h = hs.getClientById(id);
			return ResponseEntity.ok().body(h); 
		}catch( Exception e ) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping( path="" , produces = "application/json" )
	public ResponseEntity<ClientEntity> add( @RequestBody ClientEntity client ){
		try {
			ClientEntity createdClient = hs.add( client );
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdClient.getId())
                    .toUri();
			
			return ResponseEntity.created(uri).body( createdClient ); 
		}catch( Exception e ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() ); 
		}
	}
	
	@PutMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<ClientEntity> edit( @RequestBody ClientEntity client , @PathVariable("id") int id ) {
        try{
            ClientEntity updatedClient  = hs.edit( id , client);

            return ResponseEntity.ok() // OK => HTTP 200
                    .body(updatedClient);

        }catch ( Exception e ){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage()  );
        }

    }
	
	@DeleteMapping(path="/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id ){
        try {
            hs.deleteClientById(id);
            return ResponseEntity.ok() // HTTP 200
                    .body(null);
        }catch( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }
}
