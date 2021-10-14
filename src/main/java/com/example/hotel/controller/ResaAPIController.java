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

import com.example.hotel.entities.ReservationEntity;
import com.example.hotel.service.ReservationService;


@RestController()
@RequestMapping("/api/resa")
public class ResaAPIController {

	@Autowired
	ReservationService hs; 
	
	@GetMapping( path="" , produces = "application/json" )
	public Iterable<ReservationEntity> getAll(){
		return hs.getAllReservation(""); 
	}
	
	@GetMapping( path="/{id}" , produces = "application/json" )
	public ResponseEntity<ReservationEntity> get( @PathVariable("id") int id ){
		try {
			ReservationEntity h = hs.getReservationById(id);
			return ResponseEntity.ok().body(h); 
		}catch( Exception e ) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping( path="" , produces = "application/json" )
	public ResponseEntity<ReservationEntity> add( @RequestBody ReservationEntity resa ){
		try {
		
			ReservationEntity createdResa = hs.addReservation( resa.getDateDebut() , resa.getDateFin(), resa.getNumeroChambre() , resa.getClient().getId() , resa.getHotel().getId() );
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdResa.getId())
                    .toUri(); 
			
			return ResponseEntity.created(uri).body( createdResa ); 
		}catch( Exception e ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() ); 
		}
	}
	
	@PutMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<ReservationEntity> edit( @RequestBody ReservationEntity resa , @PathVariable("id") int id ) {
        try{
        	ReservationEntity updatedResa = hs.updateReservation( id , resa.getDateDebut() , resa.getDateFin(), resa.getNumeroChambre() , resa.getClient().getId() , resa.getHotel().getId() );

            return ResponseEntity.ok() // OK => HTTP 200
                    .body(updatedResa);

        }catch ( Exception e ){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage()  );
        }

    } 
	
	@DeleteMapping(path="/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id ){
        try {
            hs.deleteReservationById(id);
            return ResponseEntity.ok() // HTTP 200
                    .body(null);
        }catch( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }
}
