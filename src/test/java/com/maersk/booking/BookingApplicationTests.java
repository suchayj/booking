package com.maersk.booking;

import com.maersk.booking.repository.BookingRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

// @SpringBootTest
// @DataCassandraTest
// @CassandraUnit
class BookingApplicationTests {

	@Autowired
	BookingRepository bookingRepository;

	@Test
	public void getAllBookings() {

		// StepVerifier.create(bookingRepository.findAll()).expectSubscription().expectNextCount(0).verifyComplete();

	}

}
