package com.ticketing.bookingservice.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ticketing.bookingservice.client.InventoryClient;
import com.ticketing.bookingservice.dto.BookingRequest;
import com.ticketing.bookingservice.dto.BookingResponse;
import com.ticketing.bookingservice.dto.CustomerRequest;
import com.ticketing.bookingservice.dto.InventoryResponse;
import com.ticketing.bookingservice.entity.Customer;
import com.ticketing.bookingservice.exception.ApiException;
import com.ticketing.bookingservice.exception.ResourceNotFoundException;
import com.ticketing.bookingservice.mapper.BookingMapper;
import com.ticketing.bookingservice.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final CustomerRepository customerRepository;

    private final InventoryClient inventoryClient;

    private final BookingMapper bookingMapper;

    public BookingResponse createBooking(BookingRequest bookingRequest) {

        Customer customer = customerRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        // call inventory service to check availability
        InventoryResponse inventory = inventoryClient.getEventInventory(bookingRequest.getEventId());

        if (inventory.getCapacity() < bookingRequest.getTicketCount()) {

            throw new ApiException("Not enough tickets available", HttpStatus.BAD_REQUEST);

        }

        // calculate total
        BigDecimal totalAmount = inventory.getTicketPrice()
                .multiply(BigDecimal.valueOf(bookingRequest.getTicketCount()));

        return bookingMapper.toResponse(customer, bookingRequest, totalAmount);

    }

    // create new customer
    public Customer createCustomer(CustomerRequest req) {

        Customer customer = bookingMapper.toCustomer(req);

        return customerRepository.save(customer);

    }

}
