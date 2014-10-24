package com.multi.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.springapp.entities.Role;

@NonMultiTenancy
public interface RoleRepository extends JpaRepository<Role, Long>
{

}
