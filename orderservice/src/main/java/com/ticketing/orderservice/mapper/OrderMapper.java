package com.ticketing.orderservice.mapper;

import org.springframework.stereotype.Component;

import com.ticketing.orderservice.entity.Order;
import com.ticketing.orderservice.event.BookingEvent;

@Component
public class OrderMapper {

    // map booking event to order
    public Order toOrder(BookingEvent bookingEvent) {
        return Order.builder()
                .totalPrice(bookingEvent.getTotalPrice())
                .ticketCount(bookingEvent.getTicketCount())
                .customerId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .build();
    }
}
