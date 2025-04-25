package uk.gov.justice.fileservice.common.messaging;

import java.util.function.Function;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Collection of static utility methods for getting deep values from a {@link JsonObject}.
 */
public final class JsonObjects {

    private static final String FIELD_IS_NOT_A_TYPE = "Field %s is not a %s";

    /**
     * Private constructor to prevent misuse of utility class.
     */
    private JsonObjects() {
    }

    /**
     * Create a {@link JsonObjectBuilder} from an existing {@link JsonObject} applying the filter.
     * Only copy the field names for which the filter returns true.
     *
     * @param source {@link JsonObject} to copy fields from
     * @return a {@link JsonObjectBuilder} initialised with the fields contained in the source
     */
    public static JsonObjectBuilder createObjectBuilderWithFilter(final JsonObject source, Function<String, Boolean> filter) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        source.entrySet().stream().filter(e -> filter.apply(e.getKey())).forEach(x -> builder.add(x.getKey(), x.getValue()));
        return builder;
    }

    /**
     * Create a {@link JsonObjectBuilder} from an existing {@link JsonObject}.
     *
     * @param source {@link JsonObject} to copy fields from
     * @return a {@link JsonObjectBuilder} initialised with the fields contained in the source
     */
    public static JsonObjectBuilder createObjectBuilder(final JsonObject source) {
        return createObjectBuilderWithFilter(source, x -> true);
    }
}
