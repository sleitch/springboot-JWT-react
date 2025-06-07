package com.eve.spring.userone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eve.spring.userone.models.UserOne;
import com.eve.spring.userone.security.UserDetailsImpl;
import com.eve.spring.userone.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userService.getUserById(userDetails.getId())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateProfile(@RequestBody UserOne userIn) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        UserOne existingUser = userService.getUserById(userDetails.getId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Only update allowed fields
        existingUser.setEmail(userIn.getEmail());
        existingUser.setDisplayName(userIn.getDisplayName());
        existingUser.setFirstName(userIn.getFirstName());
        existingUser.setLastName(userIn.getLastName());
        // Do NOT update password here

        UserOne updatedUser = userService.updateUser(existingUser);
        return ResponseEntity.ok(updatedUser);
    }
}