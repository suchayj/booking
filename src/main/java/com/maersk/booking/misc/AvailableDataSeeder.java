package com.maersk.booking.misc;

import java.util.Arrays;
import java.util.List;

import com.maersk.booking.model.Available;
import com.maersk.booking.model.Booking;
import com.maersk.booking.model.ContainerTypeEnum;
import com.maersk.booking.repository.AvailableRepository;
import com.maersk.booking.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class AvailableDataSeeder implements CommandLineRunner {

    @Autowired
    AvailableRepository availableRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public void run(String... args) throws Exception {
        loadAvailableData();
        loadBookingData();
    }

    /**
     * Set Initial Available Data.
     * 
     * @return
     */
    public List<Available> availableData() {
        // return Arrays.asList(new Available(1, 20));
        return Arrays.asList(new Available(1, 20, "REEFER", 30), new Available(2, 40, "REEFER", 30),
                new Available(3, 20, "DRY", 30), new Available(4, 40, "DRY", 30)
        );
    }

    public void loadAvailableData() {
        availableRepository.deleteAll().thenMany(Flux.fromIterable(availableData())).flatMap(availableRepository::save)
                .thenMany(availableRepository.findAll())
                .subscribe(r -> System.out.println("Available Insert from CLR " + r));

    }

    /**
     * Set Initial Booking Data.
     * 
     * @return
     */
    public List<Booking> bookingInitialData() {
        return Arrays.asList(new Booking("957000001", 20, ContainerTypeEnum.REEFER, "Southampton", "Singapore", 4,
                "2021-06-03T07:02:04Z"));
    }

    public void loadBookingData() {
        bookingRepository.deleteAll().thenMany(Flux.fromIterable(bookingInitialData())).flatMap(bookingRepository::save)
                .thenMany(bookingRepository.findAll())
                .subscribe(r -> System.out.println("Booking Insert from CLR " + r));
    }

}
