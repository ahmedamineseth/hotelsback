package com.example.hotel.service;

import org.springframework.stereotype.Service;

import com.example.hotel.entities.ClientEntity;
import com.example.hotel.repositories.ClientRepository;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository cr;

    public ClientService(ClientRepository cr) {
        this.cr = cr;
    }

    public List<ClientEntity> getAllClient() {
        return (List<ClientEntity>) cr.findAll();
    }

    public ClientEntity getClientById(int id) {
        return cr.findById(id).get();
    }

    public ClientEntity add(ClientEntity client) {
        cr.save(client);
        return client;
    }

    public ClientEntity edit(int id, ClientEntity client) {
        client.setId(id);
        cr.save(client);
        return client;
    }

    public void deleteClientById(int id) {
        cr.deleteById(id);
    }
}
