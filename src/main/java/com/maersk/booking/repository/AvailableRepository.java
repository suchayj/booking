package com.maersk.booking.repository;

import com.maersk.booking.model.Available;
import com.maersk.booking.model.ContainerTypeEnum;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import reactor.core.publisher.Mono;

public interface AvailableRepository extends ReactiveCassandraRepository<Available, Integer> {

    @Query(value = "SELECT * FROM available WHERE size = ?0 AND type = ?1 ALLOW FILTERING;")
    public Mono<Available> findBySizeAndType(Integer size, ContainerTypeEnum type);

}
