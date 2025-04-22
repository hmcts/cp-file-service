package uk.gov.justice.fileservice.common.jdbc.persistence;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.naming.InitialContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FsInitialContextFactoryTest {

    private FsInitialContextFactory fsInitialContextFactory = new FsInitialContextFactory();

    @Test
    public void shouldCreateANewInitialContext() throws Exception {

        assertThat(fsInitialContextFactory.create(), is(instanceOf(InitialContext.class)));
    }
}
