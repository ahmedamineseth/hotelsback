package com.example.hotel.repositories;



import org.springframework.data.repository.CrudRepository;

import com.example.hotel.entities.AdminEntity;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {
    public AdminEntity findByUsername(String username);
}
