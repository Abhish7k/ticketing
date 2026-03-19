package com.ticketing.orderservice.event;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class BookingEvent {

    private Long userId;

    private Long eventId;

    private Long ticketCount;

    private BigDecimal totalPrice;

}
