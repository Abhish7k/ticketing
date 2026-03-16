package com.ticketing.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ticketing.inventoryservice.entity.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {

}
