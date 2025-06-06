package com.eve.spring.userone.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eve.spring.userone.models.UserOne;
import com.eve.spring.userone.repositories.UserRepository;

@Service
public class UserService {
	static protected final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    /**
     * Create a new user
     * 
     * @param user
     * @return null if unable to create
     */
    public UserOne createUser(UserOne user) {
    	
    	UserOne userNew = null;
    	
    	try {
			if (!doesUserExist(user)) {
				  
			    user.setStatus("ACTIVE");
			    user.setPwdAttempts(0);
			    user.setCreated(new Date());
			    
			    // Encode password before saving
			    user.setPwd(passwordEncoder.encode(user.getPwd()));
			    
			    userNew = userRepository.save(user);
			    userNew.setPassword(null);
			    userNew.setPwd(null);
			}
		} catch (Exception e) {
			logger.error("Unable to create user: " +user.getUsername());
		}
    	return userNew;      
    }

    /**
     * 
     * @param user
     * @return true if username exists
     */
	public boolean doesUserExist(UserOne user) {
		return userRepository.findByUsername(user.getUsername()).isPresent();    	
	}

    public UserOne getUserByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
    public Optional<UserOne> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserOne updateUser(UserOne user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public UserOne getUserByEmail(String email) {
        List<UserOne> users = userRepository.findAllByEmail(email);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        // Log warning if multiple users found
        if (users.size() > 1) {
            logger.warn("Multiple users found with email {}, returning first match", email);
        }
        return users.get(0);
    }

	public UserOne getUserByResetToken(String token) {
		  return userRepository.findByResetToken(token)
	                .orElseThrow(() -> new UsernameNotFoundException("token no good: " +token));
	}
}