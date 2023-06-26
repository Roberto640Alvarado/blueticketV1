package com.grupo9.blueTicket.services;

import java.util.List;

import com.grupo9.blueTicket.models.dtos.RoleDTO;
import com.grupo9.blueTicket.models.entities.Role;

public interface RoleService {

	void save(RoleDTO info) throws Exception;
	void delete(String id) throws Exception;
	List<Role> findAll();
}
