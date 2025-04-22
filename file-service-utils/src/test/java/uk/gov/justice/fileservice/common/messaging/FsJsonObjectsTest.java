package uk.gov.justice.fileservice.common.messaging;

import static javax.json.Json.createObjectBuilder;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link FsJsonObjects} class.
 */
public class FsJsonObjectsTest {

    @Test
    public void shouldCreateBuilderFromJsonObject() {
        JsonObject source = createObjectBuilder()
                .add("name", "test")
                .build();

        JsonObjectBuilder builder = FsJsonObjects.createObjectBuilder(source);

        assertThat(builder.build(), equalTo(source));
    }

    @Test
    public void shouldCreateBuilderFromJsonObjectWithFilter() {
        JsonObject source = createObjectBuilder()
                .add("id", "test id")
                .add("ignore1", "ignore this")
                .add("name", "test")
                .add("ignore2", "ignore this as well")
                .build();

        JsonObjectBuilder builder = FsJsonObjects.createObjectBuilderWithFilter(source, x -> !"ignore1".equals(x) && !"ignore2".equals(x));

        JsonObject actual = builder.build();
        assertThat(actual.size(), equalTo(2));
        assertThat(actual.getString("id"), equalTo(source.getString("id")));
        assertThat(actual.getString("name"), equalTo(source.getString("name")));
    }

}
