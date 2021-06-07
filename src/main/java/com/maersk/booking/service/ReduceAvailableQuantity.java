package com.maersk.booking.service;

import com.maersk.booking.model.Available;
import com.maersk.booking.repository.AvailableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReduceAvailableQuantity {

    @Autowired
    AvailableRepository availableRepository;

    public void reduceAvailable(Integer quantityToReduce) {

        // Integer totalQuantity = availableRepository.findAvailableSpace().log().block().getAvailableSpace();

        // availableRepository.save(new Available(totalQuantity - quantityToReduce))
        //         .subscribe(r -> System.out.println("r " + r));

    }
}
