package com.ticketing.orderservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ticketing.orderservice.client.InventoryServiceClient;
import com.ticketing.orderservice.entity.Order;
import com.ticketing.orderservice.event.BookingEvent;
import com.ticketing.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.ticketing.orderservice.mapper.OrderMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final InventoryServiceClient inventoryServiceClient;

    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent) {
        log.info("Received order event : {}", bookingEvent);

        // create order object for db using mapper
        Order order = orderMapper.toOrder(bookingEvent);

        orderRepository.save(order);

        // update inventory
        inventoryServiceClient.updateInventory(bookingEvent.getEventId(), bookingEvent.getTicketCount());

    }

}
