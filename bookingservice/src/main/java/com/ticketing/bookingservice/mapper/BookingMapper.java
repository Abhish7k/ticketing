package com.ticketing.bookingservice.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ticketing.bookingservice.dto.BookingRequest;
import com.ticketing.bookingservice.dto.BookingResponse;
import com.ticketing.bookingservice.dto.CustomerRequest;
import com.ticketing.bookingservice.dto.InventoryResponse;
import com.ticketing.bookingservice.entity.Customer;
import com.ticketing.bookingservice.event.BookingEvent;

@Component
public class BookingMapper {

    // map customer/req to response
    public BookingResponse toResponse(BookingEvent bookingEvent) {

        return BookingResponse.builder()
                .userId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalAmount(bookingEvent.getTotalPrice())
                .build();

    }

    // map req to customer
    public Customer toCustomer(CustomerRequest req) {

        return Customer.builder()
                .id(req.getId())
                .name(req.getName())
                .email(req.getEmail())
                .address(req.getAddress())
                .build();

    }

    // map req to event
    public BookingEvent toBookingEvent(BookingRequest bookingRequest, Customer customer,
            InventoryResponse inventory) {

        return BookingEvent.builder()
                .userId(customer.getId())
                .eventId(bookingRequest.getEventId())
                .ticketCount(bookingRequest.getTicketCount())
                .totalPrice(inventory.getTicketPrice().multiply(BigDecimal.valueOf(bookingRequest.getTicketCount())))
                .build();
    }

}
