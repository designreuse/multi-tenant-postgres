/**
 * 
 */
package com.multi.springapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Siva
 *
 */
@Entity
@Table(name = "ROLES")
public class Role extends AbstractPersistable<Integer> {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "role_name", nullable = false)
	private String roleName;

	public Role() {
	}

	public Role(String roleName) {
		this.roleName = roleName;
	}

	public Role(Integer id, String roleName) {
		super.setId(id);
		this.roleName = roleName;
	}

	@Column(name = "role_id")
	public Integer getId() {
		return super.getId();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
