package uk.gov.justice.fileservice.common.util;

import java.time.ZonedDateTime;

/**
 * Interface for clock providers.
 */
public interface Clock {

    ZonedDateTime now();
}
