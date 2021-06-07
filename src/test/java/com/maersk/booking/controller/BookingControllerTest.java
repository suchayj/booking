package com.maersk.booking.controller;

import com.maersk.booking.model.Available;
import com.maersk.booking.model.Booking;
import com.maersk.booking.model.ContainerTypeEnum;
import com.maersk.booking.repository.AvailableRepository;
import com.maersk.booking.repository.BookingRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BookingControllerTest {

    @Autowired
    BookingRepository bookingRepository;
    AvailableRepository availableRepository;

    @Test
    void testStoreBooking() {

        Booking newBooking = new Booking("957000003", 20, ContainerTypeEnum.REEFER, "Southampton", "Singapore", 4,
                null);

        Mono<Booking> savedBooking = bookingRepository.save(newBooking);

        StepVerifier.create(savedBooking.log("savedItem : ")).expectSubscription()
                .expectNextMatches(b -> (b.getContainerSize() != null) && b.getOrigin().equals("Southampton")
                        && b.getDestination().equals("Singapore")
                        && b.getContainerType().equals(ContainerTypeEnum.REEFER))
                .verifyComplete();

        // System.out.println("savedBooking " + savedBooking.block());
    }

    @Test
    void testReduceAvailable() {

        Mono<Long> saveAndCount = bookingRepository.count().doOnNext(System.out::println)
                .thenMany(bookingRepository.saveAll(Flux.just(
                        new Booking("957000003", 20, ContainerTypeEnum.REEFER, "Southampton", "Singapore", 4, null),
                        new Booking("957000004", 40, ContainerTypeEnum.REEFER, "Southampton", "Singapore", 4, null))))
                .last().flatMap(v -> bookingRepository.count()).doOnNext(System.out::println);

        StepVerifier.create(saveAndCount).expectNext(3L).verifyComplete();

    }

    @Test
    void testBookinRef() {

        String s = bookingRepository.findLastBookingRef().block().getBookingRef();
        Integer res = Integer.parseInt(s) + 1; // Increment bookingRef Counter
        String nextBookingRef = String.valueOf(res); // Convert Integer to String

        System.out.println("s " + nextBookingRef);
    }

    @Test
    void testAvailable() {

        var res = Mono.just(1).flatMap(availableRepository::findById);
        // .switchIfEmpty(Mono.error(new UserNotFoundExeception()))

        System.out.println(res);

    }

}
