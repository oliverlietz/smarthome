package org.openhab.karaf.tests.bootstrap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openhab.karaf.testing.KarafTestSupport;
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
public final class OpenhabIT extends KarafTestSupport {

    @Configuration
    public Option[] configuration() {
        return OptionUtils.combine(baseConfiguration(),
            addBootFeature("openhab")
        );
    }

    @Test
    public void testOrgOpenhabCore() {
        final Bundle bundle = findBundle("org.openhab.core");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

}
