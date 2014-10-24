package com.multi.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.springapp.entities.User;

@NonMultiTenancy
public interface UserRepository extends JpaRepository<User, Long> {


}
