package uk.gov.justice.fileservice.common.configuration;

import static uk.gov.justice.fileservice.common.configuration.FsCommonValueAnnotationDef.globalValueAnnotationOf;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.naming.NamingException;

/**
 * Looks up global jndi names in order to inject their values into @FsGlobalValue annotated
 * properties.
 */
@ApplicationScoped
public class FsGlobalValueProducer extends FsAbstractValueProducer {

    public FsGlobalValueProducer() throws NamingException {
        super();
    }

    @FsGlobalValue
    @Produces
    public String stringValueOf(final InjectionPoint ip) throws NamingException {
        return jndiValueFor(globalValueAnnotationOf(ip));
    }

    @FsGlobalValue
    @Produces
    public long longValueOf(final InjectionPoint ip) throws NamingException {
        return Long.valueOf(stringValueOf(ip));
    }

    @Override
    protected String[] jndiNamesFrom(final FsCommonValueAnnotationDef annotation) {
        return new String[]{globalJNDINameFrom(annotation)};
    }
}