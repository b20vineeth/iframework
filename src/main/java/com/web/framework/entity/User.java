package com.web.framework.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.web.framework.model.EProvider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USRDTL")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "FRSTNAM")
	private String firstName;
	
	@Column(name = "LASTNAM")
	private String lastName;
	 
	@Column(name = "PASSWORD")
	private String password;
	 
	@Column(name = "UNAM",unique = true)
	private String uname;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE")
	private Role role;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SRC")
	private EProvider provider;
	
	@Column(name = "EMAIL",unique = true)
	private String email;
	
	@Column(name = "LSTUPD")
	private Date lastupdate;

	@Column(name = "VLDFRM")
	private Date validFrom;

	@Column(name = "VLDTO")
	private Date validTo;

	@Column(name = "CRTDAT")
	private Date createdDate;
	
	@Column(name = "STATUS")
	private String status;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		// email in our case
		return uname;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	 
}
