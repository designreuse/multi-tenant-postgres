/**
 * 
 */
package com.multi.springapp.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author katsi02
 *
 */
@Entity
@Table(name = "USERS")
public class User extends AbstractPersistable<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	private Date dob;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(Integer id, String name, String email, String password, Date dob) {
		super.setId(id);
		this.name = name;
		this.email = email;
		this.password = password;
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "User [id=" + super.getId() + ", name=" + name + ", email=" + email
				+ ", dob=" + dob + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
