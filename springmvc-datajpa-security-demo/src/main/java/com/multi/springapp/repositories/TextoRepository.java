package com.multi.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.springapp.entities.Texto;

@MultiTenancy
public interface TextoRepository extends JpaRepository<Texto, Long> {

}
