package com.maersk.booking.exception;

import org.springframework.data.cassandra.CassandraInternalException;

public class BookingSaveException extends CassandraInternalException {
    public BookingSaveException(String msg) {
        super(msg);
    }

    private static final long serialVersionUID = 1L;
}