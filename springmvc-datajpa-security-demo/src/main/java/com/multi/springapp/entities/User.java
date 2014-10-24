/**
 * 
 */
package com.multi.springapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Siva
 *
 */
@Entity(name = "usuario")
public class User extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome", nullable = false)
	private String name;

	public User() {
	}

	public User(String name) {
		this.name = name;
	}

	public User(Long id, String name) {
		super.setId(id);
		this.name = name;
	}

	@Column(name = "user_id")
	public Long getId() {
		return super.getId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
