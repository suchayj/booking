package com.maersk.booking.service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

@Service
public class TimeService {

    /**
     * Get Current Date and Time in UTC Timezone.
     * 
     * @return
     */
    public String getUTCTime() {

        ZonedDateTime truncatedTimeUtc = ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS)
                .withZoneSameInstant(ZoneOffset.UTC);

        return truncatedTimeUtc.toString();
    }
}
