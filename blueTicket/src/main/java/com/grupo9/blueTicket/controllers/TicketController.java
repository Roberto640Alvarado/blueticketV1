package com.grupo9.blueTicket.controllers;

import com.grupo9.blueTicket.models.dtos.MessageDTO;
import com.grupo9.blueTicket.models.dtos.SaveEventDTO;
import com.grupo9.blueTicket.models.dtos.SaveTicketDTO;
import com.grupo9.blueTicket.models.dtos.TokenDTO;
import com.grupo9.blueTicket.models.entities.Ticket;
import com.grupo9.blueTicket.services.EventService;
import com.grupo9.blueTicket.services.TicketService;
import com.grupo9.blueTicket.utils.RequestErrorHandler;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private EventService eventService;
	
	private RequestErrorHandler errorHandler;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveTicket(@RequestBody @Valid SaveTicketDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		if (eventService.findOneById(info.getId_event()) == null) {
			return new ResponseEntity<>(
                    new MessageDTO("This event does not exists"),
                    HttpStatus.BAD_REQUEST);
		}
		try {
			ticketService.createTicket(info);
			return new ResponseEntity<>(
					new MessageDTO("Ticket created " +info), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

}
