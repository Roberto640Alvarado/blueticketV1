package com.grupo9.blueTicket.models.dtos;

import java.util.Date;

import com.grupo9.blueTicket.models.entities.Ticket;
import com.grupo9.blueTicket.models.entities.User;

import jakarta.validation.constraints.NotEmpty;

public class SaleDTO {
	
	@NotEmpty
	private String id;
	
	@NotEmpty
	private User id_user;
	
	@NotEmpty
	private Ticket id_ticket;
	
	@NotEmpty
	private Date datePurchase;
	
	@NotEmpty
	private int amountTicket;

}