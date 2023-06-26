package com.grupo9.blueTicket.services;

import java.util.List;
import java.util.UUID;

import com.grupo9.blueTicket.models.entities.User;
import com.grupo9.blueTicket.models.dtos.RegisterDTO;
import com.grupo9.blueTicket.models.entities.Token;
import com.grupo9.blueTicket.models.dtos.ActiveDTO;
import com.grupo9.blueTicket.models.dtos.LoginDTO;
import com.grupo9.blueTicket.models.dtos.PasswordDTO;

public interface UserService {

	void login(LoginDTO info) throws Exception;
	void changePassword(UUID id, PasswordDTO info) throws Exception;
	void isActive(Boolean isActive);
	User findOneByIdentifier(String identifier);
	User findByUsernameOrEmail(String username, String email);
	void register(RegisterDTO info) throws Exception;
	User findOneById(UUID id);
	List<User> findAll();
	
	void updateActive (UUID id, ActiveDTO active) throws Exception;
	
	//Token management
    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user) throws Exception;
    
  //Find User authenticated
    User findUserAuthenticated();
	Boolean comparePassword(String toCompare, String current);
}
