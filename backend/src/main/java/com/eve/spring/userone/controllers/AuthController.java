package com.eve.spring.userone.controllers;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eve.spring.userone.models.UserOne;
import com.eve.spring.userone.security.jwt.JwtTokenProvider;
import com.eve.spring.userone.services.UserService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JavaMailSender mailSender;

	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	@PostMapping("/signup")
	@CrossOrigin
	public ResponseEntity<?> registerUser(@RequestBody UserOne user) {
		UserOne newbie = userService.createUser(user);

		if (newbie == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(Map.of("error", "User already exists or cannot be created"));
		}
		return ResponseEntity.ok(newbie);
	}

	
	/**
	 * Authenticate and return token and user
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	@CrossOrigin
	public ResponseEntity<?> authenticateUser(@RequestBody UserOne user) {
		try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUsername(),	user.getPwd());

			authenticationManager.authenticate(authToken);

			//UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
		
			UserOne newbie = userService.getUserByUsername(user.getUsername());
			if (newbie == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Invalid login attempt"));
			}
			
			String jwt = jwtTokenProvider.generateToken(user.getUsername());
			
			UserAuth userAuth = new UserAuth(user.getUsername(), jwt);
			userAuth.setDisplayName(newbie.getDisplayName());
			userAuth.setId(newbie.getId());
			
			return ResponseEntity.ok(userAuth);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Authentication failed: " + e.getMessage());
		}
	}

	@PostMapping("/forgot")
	@CrossOrigin
	public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
	    String email = request.get("email");
	    
	    try {
	        UserOne user = userService.getUserByEmail(email);
	        if (user == null) {
	            // Don't reveal that the email doesn't exist
	            return ResponseEntity.ok(Map.of("message", "If an account exists with that email, a password reset link has been sent."));
	        }

	        // Generate reset token
	        String resetToken = UUID.randomUUID().toString();
	        Instant expiryDate = Instant.now().plusSeconds(3600); // 1 hour validity

	        // Save token to user
	        user.setResetToken(resetToken);
	        user.setResetTokenExpiry(expiryDate);
	        userService.updateUser(user);

	        // Send email
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo(user.getEmail());
	        mailMessage.setSubject("Password Reset Request");
	        mailMessage.setText("To reset your password, click the link below:\n\n" +
	                "http://localhost:3000/reset-password?token=" + resetToken);

	        mailSender.send(mailMessage);

	        return ResponseEntity.ok(Map.of("message", "If an account exists with that email, a password reset link has been sent."));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Map.of("error", "Error processing request"));
	    }
	}

	@PostMapping("/reset")
	@CrossOrigin
	public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
	    String token = request.get("token");
	    String newPassword = request.get("password");

	    try {
	        UserOne user = userService.getUserByResetToken(token);
	        if (user == null) {
	            return ResponseEntity.badRequest()
	                    .body(Map.of("error", "Invalid or expired reset token"));
	        }

	        // Check if token is expired
	        if (user.getResetTokenExpiry().isBefore(Instant.now())) {
	            return ResponseEntity.badRequest()
	                    .body(Map.of("error", "Reset token has expired"));
	        }

	        // Update password and clear reset token
		    user.setPwd(passwordEncoder.encode(newPassword));
		    
	        user.setResetToken(null);
	        user.setResetTokenExpiry(null);
	        userService.updateUser(user);

	        return ResponseEntity.ok(Map.of("message", "Password has been reset successfully"));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Map.of("error", "Error resetting password"));
	    }
	}

	public class UserAuth {
		Long  id;
		String accessToken;
		String displayName;
		String userName;

		public UserAuth(String username, String jwt) {
			accessToken = jwt;
			displayName = username;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

	}

}