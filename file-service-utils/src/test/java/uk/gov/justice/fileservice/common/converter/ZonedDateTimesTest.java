package uk.gov.justice.fileservice.common.converter;

import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.of;
import static java.time.ZonedDateTime.parse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link ZonedDateTimes} utility class.
 */
public class ZonedDateTimesTest {

    @Test
    public void shouldConvertZoneDateTimeToSqlTimestamp() {
        final ZonedDateTime zonedDateTime = of(LocalDateTime.now(), UTC);
        final Timestamp dateTime = ZonedDateTimes.toSqlTimestamp(zonedDateTime);

        assertThat(dateTime.toInstant().getEpochSecond(), equalTo(zonedDateTime.toInstant().getEpochSecond()));
    }

    @Test
    public void shouldConvertSqlTimestampToZoneDateTime() {
        final Timestamp timestamp = Timestamp.valueOf("2016-07-25 23:09:00.123");
        final ZonedDateTime dateTime = ZonedDateTimes.fromSqlTimestamp(timestamp);

        assertThat(dateTime.toInstant().getEpochSecond(), equalTo(timestamp.toInstant().getEpochSecond()));
    }

    @Test
    public void shouldConvertNonUtcToUtcString() {
        final String dateTime = ZonedDateTimes.toString(parse("2016-01-21T23:42:03.522+07:00"));
        assertThat(dateTime, equalTo("2016-01-21T16:42:03.522Z"));
    }

    @Test
    public void shouldConvertNonUtcToUtcString2() {
        final String dateTime = ZonedDateTimes.toString(parse("2016-07-25T23:09:01.0+05:00"));
        assertThat(dateTime, equalTo("2016-07-25T18:09:01.000Z"));
    }
}
