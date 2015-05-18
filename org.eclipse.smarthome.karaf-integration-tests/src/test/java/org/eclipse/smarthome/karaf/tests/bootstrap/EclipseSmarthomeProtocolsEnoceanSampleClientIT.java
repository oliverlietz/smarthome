package org.eclipse.smarthome.karaf.tests.bootstrap;

import org.eclipse.smarthome.karaf.testing.KarafTestSupport;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.OptionUtils;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.osgi.framework.Bundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public final class EclipseSmarthomeProtocolsEnoceanSampleClientIT extends KarafTestSupport {

    @Configuration
    public Option[] configuration() {
        return OptionUtils.combine(baseConfiguration(),
            addBootFeature("eclipse-smarthome-protocols-enocean-sample-client")
        );
    }

    @Test
    @Ignore
    public void testOrgEclipseSmarthomeProtocolsEnoceanSampleClient() {
        final Bundle bundle = findBundle("org.eclipse.smarthome.protocols.enocean.sample.client");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

}
