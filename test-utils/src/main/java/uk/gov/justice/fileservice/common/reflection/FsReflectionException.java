package uk.gov.justice.fileservice.common.reflection;

public class FsReflectionException extends RuntimeException {
    public FsReflectionException(final String message) {
        super(message);
    }

    public FsReflectionException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
