package com.dam.placeholder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam.placeholder.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	User findTopByOrderByIdDesc();

}
