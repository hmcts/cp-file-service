package uk.gov.justice.fileservice.common.cdi;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import uk.gov.justice.fileservice.common.util.FsClock;

import javax.enterprise.inject.spi.InjectionPoint;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ExtendWith(MockitoExtension.class)
public class FsLoggerProducerTest {

    @InjectMocks
    private FsLoggerProducer fsLoggerProducer;

    @Test @SuppressWarnings("unchecked")
    public void shouldCreateALoggerWithTheCorrectCallingClass() throws Exception {

        final Class callingClass = FsClock.class;
        final InjectionPoint injectionPoint = mock(InjectionPoint.class, RETURNS_DEEP_STUBS);

        when(injectionPoint.getMember().getDeclaringClass()).thenReturn(callingClass);

        final Logger logger = fsLoggerProducer.loggerProducer(injectionPoint);

        assertThat(logger.getName(), is(LoggerFactory.getLogger(callingClass).getName()));
    }
}
