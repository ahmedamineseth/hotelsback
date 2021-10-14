package com.example.hotel.repositories;


import org.springframework.data.repository.CrudRepository;

import com.example.hotel.entities.HotelEntity;

public interface HotelRepository extends CrudRepository<HotelEntity, Integer> {
    
}
