package com.ticketing.bookingservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {

    private Long userId;

    private Long eventId;

    private Long ticketCount;

    private BigDecimal totalAmount;

}
