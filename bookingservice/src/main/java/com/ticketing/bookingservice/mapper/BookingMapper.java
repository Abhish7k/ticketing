package com.ticketing.bookingservice.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ticketing.bookingservice.dto.BookingRequest;
import com.ticketing.bookingservice.dto.BookingResponse;
import com.ticketing.bookingservice.dto.CustomerRequest;
import com.ticketing.bookingservice.entity.Customer;

@Component
public class BookingMapper {

    // map customer/req to response
    public BookingResponse toResponse(Customer customer, BookingRequest bookingReq, BigDecimal totalAmount) {

        return BookingResponse.builder()
                .userId(customer.getId())
                .eventId(bookingReq.getEventId())
                .ticketCount(bookingReq.getTicketCount())
                .totalAmount(totalAmount)
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

}
