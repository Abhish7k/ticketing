package com.ticketing.inventoryservice.mapper;

import org.springframework.stereotype.Component;

import com.ticketing.inventoryservice.dto.EventInventoryResponse;
import com.ticketing.inventoryservice.dto.EventRequest;
import com.ticketing.inventoryservice.dto.VenueInventoryResponse;
import com.ticketing.inventoryservice.dto.VenueRequest;
import com.ticketing.inventoryservice.entity.Event;
import com.ticketing.inventoryservice.entity.Venue;

@Component
public class InventoryMapper {

    // map event to dto
    public EventInventoryResponse toEventResponse(Event event) {

        return EventInventoryResponse.builder()
                .eventId(event.getId())
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .ticketPrice(event.getTicketPrice())
                .build();

    }

    // map venue to dto
    public VenueInventoryResponse toVenueResponse(Venue venue) {

        return VenueInventoryResponse.builder()
                .venueId(venue.getId())
                .venueName(venue.getName())
                .leftCapacity(venue.getTotalCapacity())
                .build();

    }

    // map req to venue
    public Venue toVenue(VenueRequest req) {

        Venue venue = new Venue();
        venue.setId(req.getId());
        venue.setName(req.getName());
        venue.setAddress(req.getAddress());
        venue.setTotalCapacity(req.getTotalCapacity());

        return venue;

    }

    // map req to event
    public Event toEvent(EventRequest req, Venue venue) {

        Event event = new Event();
        event.setId(req.getId());
        event.setName(req.getName());
        event.setTotalCapacity(req.getTotalCapacity());
        event.setLeftCapacity(req.getTotalCapacity());
        event.setVenue(venue);
        event.setTicketPrice(req.getTicketPrice());

        return event;

    }

}
