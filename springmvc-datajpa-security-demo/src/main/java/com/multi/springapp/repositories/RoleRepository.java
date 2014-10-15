package com.multi.springapp.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.springapp.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Serializable>
{

}
