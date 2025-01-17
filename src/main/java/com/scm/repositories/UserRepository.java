package com.scm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByEmail(String email);

}
