package com.devsuperior.bds03.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.devsuperior.bds03.entities.Role;

public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "O perfil do usuário é um campo obrigatório")
	private String authority;

	public RoleDTO() {
	}

	public RoleDTO(Long id, String authority) {
		super();
		this.id = id;
		this.authority = authority;
	}

	public RoleDTO(Role entity) {
		super();
		this.id = entity.getId();
		this.authority = entity.getAuthority();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}