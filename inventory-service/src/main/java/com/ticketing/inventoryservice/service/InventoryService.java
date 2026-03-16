package com.ticketing.inventoryservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.ticketing.inventoryservice.dto.EventInventoryResponse;
import com.ticketing.inventoryservice.dto.VenueInventoryResponse;
import com.ticketing.inventoryservice.entity.Event;
import com.ticketing.inventoryservice.entity.Venue;
import com.ticketing.inventoryservice.repository.EventRepository;
import com.ticketing.inventoryservice.repository.VenueRepository;
import com.ticketing.inventoryservice.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

        private final EventRepository eventRepository;
        private final VenueRepository venueRepository;

        public List<EventInventoryResponse> getAllEvents() {
                List<Event> events = eventRepository.findAll();

                List<EventInventoryResponse> responses = events.stream()
                                .map(event -> EventInventoryResponse.builder()
                                                .eventId(event.getId())
                                                .event(event.getName())
                                                .capacity(event.getLeftCapacity())
                                                .venue(event.getVenue())
                                                .ticketPrice(event.getTicketPrice())
                                                .build())
                                .collect(Collectors.toList());

                return responses;
        }

        public VenueInventoryResponse getVenueById(Long venueId) {
                Venue venue = venueRepository.findById(venueId)
                                .orElseThrow(() -> new ResourceNotFoundException("Venue not found"));

                VenueInventoryResponse response = VenueInventoryResponse.builder()
                                .venueId(venue.getId())
                                .venueName(venue.getName())
                                .leftCapacity(venue.getTotalCapacity())
                                .build();

                return response;
        }

        public EventInventoryResponse getEventById(Long eventId) {
                Event event = eventRepository.findById(eventId)
                                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

                EventInventoryResponse response = EventInventoryResponse.builder()
                                .eventId(event.getId())
                                .event(event.getName())
                                .capacity(event.getLeftCapacity())
                                .venue(event.getVenue())
                                .ticketPrice(event.getTicketPrice())
                                .build();

                return response;
        }

}
