package com.ticketing.inventoryservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.inventoryservice.dto.EventInventoryResponse;
import com.ticketing.inventoryservice.dto.EventRequest;
import com.ticketing.inventoryservice.dto.VenueInventoryResponse;
import com.ticketing.inventoryservice.dto.VenueRequest;
import com.ticketing.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/inventory/events")
    public List<EventInventoryResponse> getAllEvents() {

        return inventoryService.getAllEvents();

    }

    @GetMapping("/inventory/venue/{venueId}")
    public VenueInventoryResponse getVenueById(@PathVariable Long venueId) {

        return inventoryService.getVenueById(venueId);

    }

    @GetMapping("/inventory/event/{eventId}")
    public EventInventoryResponse getEventById(@PathVariable Long eventId) {

        return inventoryService.getEventById(eventId);

    }

    @GetMapping("/inventory/event/{eventId}/inventory")
    public EventInventoryResponse getEventInventory(@PathVariable Long eventId) {

        return inventoryService.getEventInventory(eventId);

    }

    // create new venue
    @PostMapping("/inventory/venue")
    public VenueInventoryResponse createVenue(@RequestBody VenueRequest req) {

        return inventoryService.createVenue(req);

    }

    // create new event
    @PostMapping("/inventory/event")
    public EventInventoryResponse createEvent(@RequestBody EventRequest req) {

        return inventoryService.createEvent(req);

    }

}
