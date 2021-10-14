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

import com.example.hotel.entities.HotelEntity;
import com.example.hotel.service.HotelService;


@RestController()
@RequestMapping("/api/hotel")
public class HotelAPIController {

	@Autowired
	HotelService hs; 
	
	@GetMapping( path="" , produces = "application/json" )
	public Iterable<HotelEntity> getAll(){
		return hs.getAllHotel(); 
	}
	
	@GetMapping( path="/{id}" , produces = "application/json" )
	public ResponseEntity<HotelEntity> get( @PathVariable("id") int id ){
		try {
			HotelEntity h = hs.getHotelById(id);
			return ResponseEntity.ok().body(h); 
		}catch( Exception e ) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping( path="" , produces = "application/json" )
	public ResponseEntity<HotelEntity> add( @RequestBody HotelEntity hotel ){
		try {
			HotelEntity createdHotel = hs.addHotel( hotel );
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdHotel.getId())
                    .toUri();
			
			return ResponseEntity.created(uri).body( createdHotel ); 
		}catch( Exception e ) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() ); 
		}
	}
	
	@PutMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<HotelEntity> edit( @RequestBody HotelEntity hotel , @PathVariable("id") int id ) {
        try{
            HotelEntity updatedHotel  = hs.updateHotel( id , hotel);

            return ResponseEntity.ok() // OK => HTTP 200
                    .body(updatedHotel);

        }catch ( Exception e ){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage()  );
        }

    }
	
	@DeleteMapping(path="/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id ){
        try {
            hs.deleteHotelById(id);
            return ResponseEntity.ok() // HTTP 200
                    .body(null);
        }catch( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }
}
