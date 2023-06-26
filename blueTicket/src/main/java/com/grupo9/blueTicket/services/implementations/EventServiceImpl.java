package com.grupo9.blueTicket.services.implementations;
import com.grupo9.blueTicket.models.dtos.EventDTO;
import com.grupo9.blueTicket.models.entities.Event;
import com.grupo9.blueTicket.models.entities.Ticket;
import com.grupo9.blueTicket.repositories.EventRepository;
//import com.grupo9.blueTicket.repositories.TicketRepository;
//import com.grupo9.blueTicket.repositories.TicketRepository;
import com.grupo9.blueTicket.services.EventService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
/*
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    public EventServiceImpl(EventRepository eventRepository, TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = convertToEvent(eventDTO);
        Event createdEvent = eventRepository.save(event);
        return convertToEventDTO(createdEvent);
    }

    @Override
    public EventDTO getEventById(UUID eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + eventId));
        return convertToEventDTO(event);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(this::convertToEventDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EventDTO updateEvent(UUID eventId, EventDTO eventDTO) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + eventId));

        Event updatedEvent = convertToEvent(eventDTO);
        updatedEvent.setId(existingEvent.getId());

        Event savedEvent = eventRepository.save(updatedEvent);
        return convertToEventDTO(savedEvent);
    }

    @Override
    public void deleteEvent(UUID eventId) {
        eventRepository.deleteById(eventId);
    }

    private Event convertToEvent(EventDTO eventDTO) {
        Event event = new Event();
        BeanUtils.copyProperties(eventDTO, event);
        return event;
    }

    private EventDTO convertToEventDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        BeanUtils.copyProperties(event, eventDTO);
        return eventDTO;
    }

    private void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private String[] getNullPropertyNames(Object source) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        HashSet<String> nullProperties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = beanWrapper.getPropertyValue(propertyName);
            if (propertyValue == null) {
                nullProperties.add(propertyName);
            }
        }
        return nullProperties.toArray(new String[0]);
    }

    @Override
    public List<EventDTO> getAttendedEventsByUserId(UUID userId) {
        List<Ticket> tickets = ticketRepository.findByUserId(userId);
        List<Event> attendedEvents = tickets.stream()
                .map(Ticket::getEvent)
                .collect(Collectors.toList());
        return attendedEvents.stream()
                .map(this::convertToEventDTO)
                .collect(Collectors.toList());
    }
}*/
