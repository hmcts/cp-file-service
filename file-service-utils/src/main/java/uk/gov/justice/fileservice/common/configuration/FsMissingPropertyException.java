package uk.gov.justice.fileservice.common.configuration;

public class FsMissingPropertyException extends RuntimeException {

    private static final long serialVersionUID = -2539036250375591049L;

    public FsMissingPropertyException(final String message) {
        super(message);
    }
}