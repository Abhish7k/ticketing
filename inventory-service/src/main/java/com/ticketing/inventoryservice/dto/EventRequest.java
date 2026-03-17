package com.ticketing.inventoryservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequest {

    private Long id;

    private String name;

    private Long totalCapacity;

    private Long venueId;

    private BigDecimal ticketPrice;

}
