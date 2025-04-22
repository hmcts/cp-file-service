package uk.gov.justice.fileservice.common.configuration;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static uk.gov.justice.fileservice.common.configuration.FsCommonValueAnnotationDef.NULL_DEFAULT;

import java.lang.annotation.Retention;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

@Qualifier
@Retention(RUNTIME)
public @interface FsGlobalValue {

    /**
     * Bundle key
     *
     * @return a valid key
     */
    @Nonbinding String key() default "";

    /**
     * Default value if not provided
     *
     * @return default value or ""
     */
    @Nonbinding String defaultValue() default NULL_DEFAULT;
}