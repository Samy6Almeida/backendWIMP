package com.whereismyparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whereismyparty.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
