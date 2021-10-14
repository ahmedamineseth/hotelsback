package com.example.hotel.repositories;



import org.springframework.data.repository.CrudRepository;

import com.example.hotel.entities.ClientEntity;

public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
    
}
