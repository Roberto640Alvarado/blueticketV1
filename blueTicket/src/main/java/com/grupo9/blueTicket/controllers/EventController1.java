package com.grupo9.blueTicket.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo9.blueTicket.models.dtos.ActiveEventDTO;
import com.grupo9.blueTicket.models.dtos.MessageDTO;
import com.grupo9.blueTicket.models.dtos.SaveEventDTO;
import com.grupo9.blueTicket.models.entities.Event;
import com.grupo9.blueTicket.services.EventService;
import com.grupo9.blueTicket.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventController1 {
	@Autowired
	private EventService eventService;
	
	private RequestErrorHandler errorHandler;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveEvent(@RequestBody @Valid SaveEventDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		if (eventService.findOneByTitle(info.getTitle()) != null) {
			return new ResponseEntity<>(
                    new MessageDTO("This event already exists"),
                    HttpStatus.BAD_REQUEST);
		}
		try {
			eventService.createEvent(info);
			return new ResponseEntity<>(
					new MessageDTO("Event created " +info), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateEvent(@PathVariable(name = "id") UUID id,@RequestBody @Valid SaveEventDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		Event event = eventService.findOneById(id);
		
		if (event == null) {
			return new ResponseEntity<>(
		    		new MessageDTO("This event was not found"),
		    		HttpStatus.NOT_FOUND);
		}
		try {
			eventService.updateCreatedEvent(id,info);
			return new ResponseEntity<>(
					new MessageDTO("This event was updated " +info), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	@PatchMapping("/active")
	public ResponseEntity<?> setActiveEvent(@RequestBody @Valid ActiveEventDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		Event event = eventService.findOneByTitle(info.getTitle());
		try {
			if (event != null) {
				eventService.updateActiveEvent(event.getId(), info);
			}else {
				return new ResponseEntity<>(
	                    new MessageDTO("This event does not exists"),
	                    HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(
	                new MessageDTO("The status has change"),
	                HttpStatus.OK);
		} catch (Exception e) {
			 e.printStackTrace();
		        return new ResponseEntity<>(
		                new MessageDTO("Internal Server Error"),
		                HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/allEvents")
	public ResponseEntity<?> getAllEvents(){
		List<Event> allEvents = eventService.getAllEvents();
		return new ResponseEntity<>(allEvents, HttpStatus.OK);
	}
	
	@GetMapping("/event/{id}")
	public ResponseEntity<?> getEventById(@PathVariable(name = "id") UUID id){
		Event event = eventService.findOneById(id);
		if(event != null) {
			return ResponseEntity.ok(event);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
