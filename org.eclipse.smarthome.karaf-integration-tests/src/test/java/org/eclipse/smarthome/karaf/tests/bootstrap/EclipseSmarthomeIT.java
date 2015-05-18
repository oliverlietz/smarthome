package org.eclipse.smarthome.karaf.tests.bootstrap;

import org.eclipse.smarthome.karaf.testing.KarafTestSupport;
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
public final class EclipseSmarthomeIT extends KarafTestSupport {

    @Configuration
    public Option[] configuration() {
        return OptionUtils.combine(baseConfiguration(),
            addBootFeature("eclipse-smarthome")
        );
    }

    @Test
    public void testOrgEclipseSmarthomeCore() {
        final Bundle bundle = findBundle("org.eclipse.smarthome.core");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

    @Test
    public void testOrgEclipseSmarthomeCoreAutoupdate() {
        final Bundle bundle = findBundle("org.eclipse.smarthome.core.autoupdate");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

    @Test
    public void testOrgEclipseSmarthomeCoreBindingXml() {
        final Bundle bundle = findBundle("org.eclipse.smarthome.core.binding.xml");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

    @Test
    public void testOrgEclipseSmarthomeCoreLibrary() {
        final Bundle bundle = findBundle("org.eclipse.smarthome.core.library");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

    @Test
    public void testOrgEclipseSmarthomeCorePersistence() {
        final Bundle bundle = findBundle("org.eclipse.smarthome.core.persistence");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

    @Test
    public void testOrgEclipseSmarthomeCoreScheduler() {
        final Bundle bundle = findBundle("org.eclipse.smarthome.core.scheduler");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

    @Test
    public void testOrgEclipseSmarthomeCoreThing() {
        final Bundle bundle = findBundle("org.eclipse.smarthome.core.thing");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

    @Test
    public void testOrgEclipseSmarthomeCoreThingXml() {
        final Bundle bundle = findBundle("org.eclipse.smarthome.core.thing.xml");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

    @Test
    public void testOrgEclipseSmarthomeCoreTransform() {
        final Bundle bundle = findBundle("org.eclipse.smarthome.core.transform");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

    @Test
    public void testOrgEclipseSmarthomeConfigCore() {
        final Bundle bundle = findBundle("org.eclipse.smarthome.config.core");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

    @Test
    public void testOrgEclipseSmarthomeConfigXml() {
        final Bundle bundle = findBundle("org.eclipse.smarthome.config.xml");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

    @Test
    public void testOrgEclipseSmarthomeIoConsole() {
        final Bundle bundle = findBundle("org.eclipse.smarthome.io.console");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

    @Test
    public void testOrgEclipseSmarthomeIoNet() {
        final Bundle bundle = findBundle("org.eclipse.smarthome.io.net");
        assertNotNull(bundle);
        assertEquals(Bundle.ACTIVE, bundle.getState());
    }

    // TODO test bundle dependencies

}
