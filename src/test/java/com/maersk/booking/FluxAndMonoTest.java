package com.maersk.booking;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

public class FluxAndMonoTest {

    @Test
    public void fluxTest() {
        Flux<String> sFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                // .concatWith(Flux.error(new RuntimeException("Exception Occured"))).concatWith(Flux.just("After Error"))
                .log();

        sFlux.subscribe(System.out::println, (e) -> System.err.println("Exception is: " + e),
                () -> System.out.println("Completed"));
    }

}
