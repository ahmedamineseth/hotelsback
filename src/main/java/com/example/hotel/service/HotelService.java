package com.example.hotel.service;

import org.springframework.stereotype.Service;

import com.example.hotel.entities.HotelEntity;
import com.example.hotel.repositories.HotelRepository;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hr;

    public HotelService(HotelRepository hr) {
        this.hr = hr;
    }

    public void checkHotel( HotelEntity hotel ) throws Exception {
    	if( hotel.getNom().length() < 3 ) {
    		throw new Exception( "Hotel name invalid" );
    	}
    	
    	if( hotel.getEtoiles() < 1 ) {
    		throw new Exception( "Nb Etoiles Invalid" );
    	}
    	
    }
    
    public List<HotelEntity> getAllHotel() {
        return (List<HotelEntity>) hr.findAll();
    }

    public HotelEntity getHotelById(int id) {
        return hr.findById(id).get();
    }

    public HotelEntity addHotel( HotelEntity hotel ) throws Exception  {
    	checkHotel(hotel);
    	hr.save(hotel);
        return hotel;
    }
    
    public HotelEntity updateHotel(int id, HotelEntity hotel ) throws Exception  {
    	checkHotel(hotel);
    	hotel.setId(id);
        hr.save(hotel);
        return hotel;
    }

    public void deleteHotelById(int id) {
    	hr.deleteById(id);
    }


}
