package com.grupo9.blueTicket.repositories;

import com.grupo9.blueTicket.models.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
	public Event findOneByTitle(String title);
}