package com.ticketing.bookingservice.service;

import org.springframework.stereotype.Service;

import com.ticketing.bookingservice.client.InventoryClient;
import com.ticketing.bookingservice.dto.BookingRequest;
import com.ticketing.bookingservice.dto.BookingResponse;
import com.ticketing.bookingservice.dto.CustomerRequest;
import com.ticketing.bookingservice.dto.InventoryResponse;
import com.ticketing.bookingservice.entity.Customer;
import com.ticketing.bookingservice.event.BookingEvent;
import com.ticketing.bookingservice.exception.ApiException;
import com.ticketing.bookingservice.exception.ResourceNotFoundException;
import com.ticketing.bookingservice.mapper.BookingMapper;
import com.ticketing.bookingservice.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {

    private final CustomerRepository customerRepository;

    private final InventoryClient inventoryClient;

    private final BookingMapper bookingMapper;

    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;

    public BookingResponse createBooking(BookingRequest bookingRequest) {

        Customer customer = customerRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        // call inventory service to check availability
        InventoryResponse inventory = inventoryClient.getEventInventory(bookingRequest.getEventId());

        if (inventory.getCapacity() < bookingRequest.getTicketCount()) {

            throw new ApiException("Not enough tickets available", HttpStatus.BAD_REQUEST);

        }

        // create booking
        final BookingEvent bookingEvent = createBookingEvent(bookingRequest, customer, inventory);

        // send booking event to order service on kafka topic
        kafkaTemplate.send("booking", bookingEvent);

        log.info("Booking event sent to kafka topic: {}", bookingEvent);

        // return booking response
        return bookingMapper.toResponse(bookingEvent);
    }

    // create new customer
    public Customer createCustomer(CustomerRequest req) {

        Customer customer = bookingMapper.toCustomer(req);

        return customerRepository.save(customer);

    }

    private BookingEvent createBookingEvent(BookingRequest bookingRequest, Customer customer,
            InventoryResponse inventory) {

        return bookingMapper.toBookingEvent(bookingRequest, customer, inventory);
    }

}
