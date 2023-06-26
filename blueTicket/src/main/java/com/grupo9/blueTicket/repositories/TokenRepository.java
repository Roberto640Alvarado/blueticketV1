package com.grupo9.blueTicket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.grupo9.blueTicket.models.entities.Token;
import com.grupo9.blueTicket.models.entities.User;

public interface TokenRepository extends ListCrudRepository<Token, UUID>{
	List<Token> findByUserAndActive(User user, Boolean active);

}
