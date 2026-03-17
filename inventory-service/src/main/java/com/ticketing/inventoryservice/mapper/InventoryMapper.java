package com.ticketing.inventoryservice.mapper;

import org.springframework.stereotype.Component;

import com.ticketing.inventoryservice.dto.EventInventoryResponse;
import com.ticketing.inventoryservice.dto.VenueInventoryResponse;
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

}
