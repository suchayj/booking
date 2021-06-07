package com.maersk.booking.service;

import com.maersk.booking.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NextBookingRef {

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Increment Booking Ref and Get Booking Reference to Save.
     * 
     * @return
     */
    public String getNextBookingRef() {

        String s = bookingRepository.findLastBookingRef().block().getBookingRef();
        Integer res = Integer.parseInt(s) + 1; // Increment bookingRef Counter
        String nextBookingRef = String.valueOf(res); // Convert Integer to String

        return nextBookingRef;
    }

}
