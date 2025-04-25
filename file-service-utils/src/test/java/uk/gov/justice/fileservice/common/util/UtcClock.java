package uk.gov.justice.fileservice.common.util;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.MILLIS;

import java.time.ZonedDateTime;

import javax.enterprise.context.ApplicationScoped;

/**
 * Implementation of a clock that always generates a {@link ZonedDateTime} in UTC.
 */
@ApplicationScoped
public class UtcClock implements Clock {

    /**
     * @return The current UTC time truncated to milliseconds
     */
    @Override
    public ZonedDateTime now() {
        return ZonedDateTime.now(UTC).truncatedTo(MILLIS);
    }
}
