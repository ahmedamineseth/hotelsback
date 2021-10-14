package com.example.hotel.service;


import org.springframework.stereotype.Service;

import com.example.hotel.entities.ClientEntity;
import com.example.hotel.entities.HotelEntity;
import com.example.hotel.entities.ReservationEntity;
import com.example.hotel.repositories.ReservationRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository rr;

    public ReservationService(ReservationRepository rr) {
        this.rr = rr;
    }

    public List<ReservationEntity> getAllReservation(String search) {
        if (search == null || search.length() == 0) {
            return (List<ReservationEntity>) rr.findAll();
        } else {
            //return (List<ReservationEntity>) rr.findByClientNomComplet(search);
        	return null; 
        }

    }

    public ReservationEntity getReservationById(int id) {
        return rr.findById(id).get();
    }

    private void checkResaConflicts( ReservationEntity resa ) throws Exception {
    	if( rr.getConflitsOfResa( resa.getNumeroChambre() , resa.getDateDebut(), resa.getDateFin() , resa.getHotel().getId() ).size()  > 0 ) {
    		System.out.println( "Chambre déjà réservée" );
    		throw new Exception("Chambre déjà réservée");
    	}
    }
    
    public ReservationEntity addReservation(Date dateDebut, Date dateFin, int numeroChambre, int client, int hotel) throws Exception {
        ReservationEntity reservation = new ReservationEntity();
        reservation.setDateDebut(dateDebut);
        reservation.setDateFin(dateFin);
        reservation.setNumeroChambre(numeroChambre);
        ClientEntity client1 = new ClientEntity();
        client1.setId(client);
        reservation.setClient(client1);
        HotelEntity hotel1 = new HotelEntity();
        hotel1.setId(hotel);
        reservation.setHotel(hotel1);
        
        checkResaConflicts( reservation ); 
        
        rr.save(reservation);
        return  reservation;
    }

    public ReservationEntity updateReservation(int id, Date dateDebut, Date dateFin, int numeroChambre, int client, int hotel) {
        ReservationEntity reservation = this.getReservationById(id);

        reservation.setDateDebut(dateDebut);
        reservation.setDateFin(dateFin);
        reservation.setNumeroChambre(numeroChambre);
        ClientEntity client1 = new ClientEntity();
        client1.setId(client);
        reservation.setClient(client1);
        HotelEntity hotel1 = new HotelEntity();
        hotel1.setId(hotel);
        reservation.setHotel(hotel1);
        rr.save(reservation);
        return  reservation;
    }

    public void deleteReservationById(int id) {
        rr.deleteById(id);
    }
}
