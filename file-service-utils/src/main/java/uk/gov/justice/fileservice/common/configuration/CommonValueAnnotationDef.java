package uk.gov.justice.fileservice.common.configuration;

import javax.enterprise.inject.spi.InjectionPoint;

class CommonValueAnnotationDef {

    static final String NULL_DEFAULT = "_null_default";

    private String key;
    private String defaultValue;

    private CommonValueAnnotationDef(final String key, final String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    static CommonValueAnnotationDef globalValueAnnotationOf(final InjectionPoint ip) {
        final GlobalValue annotation = ip.getAnnotated().getAnnotation(GlobalValue.class);
        return new CommonValueAnnotationDef(annotation.key(), annotation.defaultValue());
    }

    String key() {
        return key;
    }

    String defaultValue() {
        return defaultValue;
    }
}