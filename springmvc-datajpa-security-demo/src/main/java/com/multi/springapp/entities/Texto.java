package com.multi.springapp.entities;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity(name = "texto")
public class Texto extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;
	
	private String texto;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}
