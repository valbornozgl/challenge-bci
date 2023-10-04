package com.bci.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bci.challenge.entities.User;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByToken(String token);

}
