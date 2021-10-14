package com.example.hotel.security;

import com.example.hotel.entities.AdminEntity;
import com.example.hotel.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String usernameField ) throws
            UsernameNotFoundException {
    	AdminEntity user = userRepository.findByUsername(usernameField);
        System.out.println(usernameField);
        System.out.println(user);
        if(user == null) {
            throw new UsernameNotFoundException("No user named " + usernameField);
        } else {
            return new UserDetailsImpl(user);
        }
    }

}
