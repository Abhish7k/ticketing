package com.ticketing.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueRequest {

    private Long id;

    private String name;

    private String address;

    private Long totalCapacity;

}
