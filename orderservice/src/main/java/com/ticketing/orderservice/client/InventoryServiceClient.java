package com.ticketing.orderservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceClient {

    private final RestClient restClient = RestClient.create();

    @Value("${inventory.service.url}")
    private String baseUrl;

    public ResponseEntity<Void> updateInventory(Long eventId, Long ticketCount) {

        // update inventory capacity
        restClient.put()
                .uri(baseUrl + "/event/" + eventId + "/capacity/" + ticketCount)
                .retrieve()
                .toBodilessEntity();

        return ResponseEntity.ok().build();

    }

}
