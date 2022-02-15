package com.devsuperior.bds03.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.devsuperior.bds03.dto.RoleDTO;
import com.devsuperior.bds03.entities.Role;
import com.devsuperior.bds03.repositories.RoleRepository;
import com.devsuperior.bds03.services.exceptions.DatabaseException;
import com.devsuperior.bds03.services.exceptions.ResourceNotFoundException;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public List<RoleDTO> findAll() {

		List<Role> listEntity = roleRepository.findAll();

		List<RoleDTO> listRoleDto = new ArrayList<>();

		listEntity.forEach(elementEntity -> listRoleDto.add(new RoleDTO(elementEntity)));

		return listRoleDto;

	}

	public RoleDTO findById(Long id) {

		Optional<Role> objOptional = roleRepository.findById(id);

		Role entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("Role " + id + " não encontrada"));

		return new RoleDTO(entity);

	}

	public RoleDTO insert(RoleDTO roleDTO) {

		Role entity = new Role();

		copyDtoToEntity(roleDTO, entity);

		entity = roleRepository.save(entity);

		return new RoleDTO(entity);

	}

	public RoleDTO update(Long id, RoleDTO roleDTO) {

		try {
			Role entity = roleRepository.getOne(id);

			copyDtoToEntity(roleDTO, entity);

			entity = roleRepository.save(entity);

			return new RoleDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Role " + id + " não encontrada");
		}
	}

	public void delete(Long id) {

		try {
			roleRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Role " + id + " não encontrada");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}

	public void copyDtoToEntity(RoleDTO dto, Role entity) {

		entity.setAuthority(dto.getAuthority());

	}

}
