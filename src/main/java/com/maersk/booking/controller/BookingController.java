package com.maersk.booking.controller;

import static com.maersk.booking.constants.BookingConstant.CHECK_AVAILABLE;
import static com.maersk.booking.constants.BookingConstant.GET_ALL_BOOKING;
import static com.maersk.booking.constants.BookingConstant.GET_BOOKING;
import static com.maersk.booking.constants.BookingConstant.ROOT_CONTEXT;

import javax.validation.Valid;

import com.maersk.booking.exception.BookingExceptionController;
import com.maersk.booking.exception.BookingSaveException;
import com.maersk.booking.model.Available;
import com.maersk.booking.model.Booking;
import com.maersk.booking.repository.AvailableRepository;
import com.maersk.booking.repository.BookingRepository;
import com.maersk.booking.service.NextBookingRef;
import com.maersk.booking.service.ReduceAvailableQuantity;
import com.maersk.booking.service.TimeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = ROOT_CONTEXT)
public class BookingController {

    private BookingRepository bookingRepository;
    private AvailableRepository availableRepository;

    private TimeService timeService;
    private NextBookingRef nextBookingRef;

    public BookingController(BookingRepository bookingRepository, AvailableRepository availableRepository,
            TimeService timeService, NextBookingRef nextBookingRef) {
        this.bookingRepository = bookingRepository;
        this.availableRepository = availableRepository;
        this.timeService = timeService;
        this.nextBookingRef = nextBookingRef;
    }

    /**
     * Check Available Containers.
     * 
     * @return
     */
    @PostMapping(path = CHECK_AVAILABLE)
    public Mono<Available> checkAvailable(@RequestBody Booking booking) {
        return availableRepository.findBySizeAndType(booking.getContainerSize(), booking.getContainerType());
    }

    /**
     * Store Booking and increment bookingRef, add timestamp(UTC) and reduce
     * Available.
     * 
     * @param booking
     * @return
     */
    @PostMapping()
    public Mono<Booking> storeBooking(@RequestBody @Valid Booking booking) {

        Available available = availableRepository
                .findBySizeAndType(booking.getContainerSize(), booking.getContainerType()).block();

        if (available.getAvailableSpace() >= booking.getQuantity()) {
            booking.setBookingRef(nextBookingRef.getNextBookingRef());
            booking.setTimestamp(timeService.getUTCTime());
            reduceAvailable(booking);

            return bookingRepository.save(booking).log();
        } else {
            throw new BookingSaveException(null);
        }

    }

    /**
     * Reduce Available.
     * 
     * @param booking
     */
    public void reduceAvailable(Booking booking) {

        availableRepository.findBySizeAndType(booking.getContainerSize(), booking.getContainerType()).map(item -> {
            item.setAvailableSpace(item.getAvailableSpace() - booking.getQuantity());
            return item;
        }).flatMap(item -> {
            return availableRepository.save(item).log();
        }).subscribe();
    }

    /**
     * Get Latest bookingRef.
     * 
     * @return
     */
    @GetMapping(path = GET_BOOKING)
    public Mono<Booking> getBooking() {
        return bookingRepository.findLastBookingRef();
    }

    /**
     * Get All Bookings.
     * 
     * @return
     */
    @GetMapping(path = GET_ALL_BOOKING)
    public Flux<Booking> getAllBooking() throws Exception {
        return bookingRepository.findAll().log();
    }

}
