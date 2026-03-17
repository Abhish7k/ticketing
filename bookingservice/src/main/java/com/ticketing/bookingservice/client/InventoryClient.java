package com.ticketing.bookingservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.ticketing.bookingservice.dto.InventoryResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InventoryClient {

    private final RestClient restClient;

    @Value("${inventory.service.url}")
    private String baseUrl;

    // get inventory for specific event id
    public InventoryResponse getEventInventory(Long eventId) {

        return restClient.get()
                .uri(baseUrl + "/inventory/event/" + eventId + "/inventory")
                .retrieve()
                .body(InventoryResponse.class);

    }

}
