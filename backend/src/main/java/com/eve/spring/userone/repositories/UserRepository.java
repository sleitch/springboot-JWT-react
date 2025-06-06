package com.eve.spring.userone.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eve.spring.userone.models.UserOne;

@Repository
public interface UserRepository extends JpaRepository<UserOne, Long> {
    @Query("SELECT u FROM UserOne u WHERE u.username = :username")
    Optional<UserOne> findByUsername(@Param("username") String username);

    @Query(value = "SELECT u FROM UserOne u WHERE u.email = :email")
    List<UserOne> findAllByEmail(@Param("email") String email);

    // Or if you want just the first match:
    @Query(value = "SELECT u FROM UserOne u WHERE u.email = :email ORDER BY u.id ASC LIMIT 1", 
           nativeQuery = true)
    Optional<UserOne> findFirstByEmail(@Param("email") String email);

    @Query("SELECT u FROM UserOne u WHERE u.resetToken = :token")
    Optional<UserOne> findByResetToken(@Param("token") String token);
}