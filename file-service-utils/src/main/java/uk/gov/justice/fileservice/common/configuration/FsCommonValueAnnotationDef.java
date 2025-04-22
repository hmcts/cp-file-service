package uk.gov.justice.fileservice.common.configuration;

import javax.enterprise.inject.spi.InjectionPoint;

class FsCommonValueAnnotationDef {

    static final String NULL_DEFAULT = "_null_default";

    private String key;
    private String defaultValue;

    private FsCommonValueAnnotationDef(final String key, final String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    static FsCommonValueAnnotationDef globalValueAnnotationOf(final InjectionPoint ip) {
        final FsGlobalValue annotation = ip.getAnnotated().getAnnotation(FsGlobalValue.class);
        return new FsCommonValueAnnotationDef(annotation.key(), annotation.defaultValue());
    }

    String key() {
        return key;
    }

    String defaultValue() {
        return defaultValue;
    }
}