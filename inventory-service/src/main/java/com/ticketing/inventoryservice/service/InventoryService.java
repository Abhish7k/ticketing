package com.ticketing.inventoryservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ticketing.inventoryservice.dto.EventInventoryResponse;
import com.ticketing.inventoryservice.dto.EventRequest;
import com.ticketing.inventoryservice.dto.VenueInventoryResponse;
import com.ticketing.inventoryservice.dto.VenueRequest;
import com.ticketing.inventoryservice.entity.Event;
import com.ticketing.inventoryservice.entity.Venue;
import com.ticketing.inventoryservice.exception.ResourceNotFoundException;
import com.ticketing.inventoryservice.mapper.InventoryMapper;
import com.ticketing.inventoryservice.repository.EventRepository;
import com.ticketing.inventoryservice.repository.VenueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

        private final EventRepository eventRepository;

        private final VenueRepository venueRepository;

        private final InventoryMapper inventoryMapper;

        public List<EventInventoryResponse> getAllEvents() {
                List<Event> events = eventRepository.findAll();

                return events.stream()
                                .map(inventoryMapper::toEventResponse)
                                .collect(Collectors.toList());

        }

        public VenueInventoryResponse getVenueById(Long venueId) {
                Venue venue = venueRepository.findById(venueId)
                                .orElseThrow(() -> new ResourceNotFoundException("Venue not found"));

                return inventoryMapper.toVenueResponse(venue);

        }

        public EventInventoryResponse getEventById(Long eventId) {
                Event event = eventRepository.findById(eventId)
                                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

                return inventoryMapper.toEventResponse(event);

        }

        public EventInventoryResponse getEventInventory(Long eventId) {
                Event event = eventRepository.findById(eventId)
                                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

                return inventoryMapper.toEventResponse(event);

        }

        // create new venue
        public VenueInventoryResponse createVenue(VenueRequest req) {

                Venue venue = inventoryMapper.toVenue(req);

                venue = venueRepository.save(venue);

                return inventoryMapper.toVenueResponse(venue);

        }

        // create new event
        public EventInventoryResponse createEvent(EventRequest req) {

                Venue venue = venueRepository.findById(req.getVenueId())
                                .orElseThrow(() -> new ResourceNotFoundException("Venue not found"));

                Event event = inventoryMapper.toEvent(req, venue);

                event = eventRepository.save(event);

                return inventoryMapper.toEventResponse(event);

        }

}
