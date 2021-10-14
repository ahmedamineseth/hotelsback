package com.example.hotel.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.hotel.entities.ReservationEntity;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Integer> {
	
	//public Iterator<ReservationEntity> findByNumeroChambre( int numeroChambre ); 
	
	@Query(value=" SELECT resa.*  FROM resa WHERE hotel = ?4 AND numero_chambre=?1 AND ( datedebut BETWEEN ?2 AND ?3 OR datefin BETWEEN ?2 AND ?3)", nativeQuery=true)
	List<ReservationEntity> getConflitsOfResa( int num , Date ddeb , Date dfin , int hotel );
	
	//findByNumeroChambreAndDateDebutBetweenAndDateFinBetween
    
}





























