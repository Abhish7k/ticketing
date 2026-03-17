package com.ticketing.bookingservice.service;

import org.springframework.stereotype.Service;

import com.ticketing.bookingservice.dto.BookingRequest;
import com.ticketing.bookingservice.dto.BookingResponse;

@Service
public class BookingService {

    public BookingResponse createBooking(BookingRequest bookingRequest) {
        return BookingResponse.builder()
                .build();
    }

}
