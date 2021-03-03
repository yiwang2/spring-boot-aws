package com.genesys.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genesys.application.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
