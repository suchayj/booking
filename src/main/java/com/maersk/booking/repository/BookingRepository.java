package com.maersk.booking.repository;

import com.maersk.booking.model.Booking;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import reactor.core.publisher.Mono;

public interface BookingRepository extends ReactiveCassandraRepository<Booking, String> {

    @Query("SELECT MAX(booking_ref) as booking_ref FROM bookings LIMIT 1")
    public Mono<Booking> findLastBookingRef();

}
