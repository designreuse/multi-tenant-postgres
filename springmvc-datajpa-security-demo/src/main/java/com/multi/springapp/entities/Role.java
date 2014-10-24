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
@Entity(name = "role")
public class Role extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "role_name", nullable = false)
	private String roleName;

	public Role() {
	}

	public Role(String roleName) {
		this.roleName = roleName;
	}

	public Role(Long id, String roleName) {
		super.setId(id);
		this.roleName = roleName;
	}

	@Column(name = "role_id")
	public Long getId() {
		return super.getId();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
