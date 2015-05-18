package org.openhab.karaf.testing;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

import javax.inject.Inject;

import org.apache.karaf.features.BootFinished;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;
import org.ops4j.pax.exam.util.Filter;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.streamBundle;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.editConfigurationFileExtend;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.editConfigurationFilePut;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.keepRuntimeFolder;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel;
import static org.ops4j.pax.tinybundles.core.TinyBundles.bundle;

public abstract class KarafTestSupport {

    @Inject
    protected BundleContext bundleContext;

    @Inject
    protected ConfigurationAdmin configurationAdmin;

    @Inject
    @Filter(timeout = 300000)
    BootFinished bootFinished;

    public static final String KARAF_GROUP_ID = "org.apache.karaf";

    public static final String KARAF_ARTIFACT_ID = "apache-karaf";

    public static final String KARAF_NAME = "Apache Karaf";

    public KarafTestSupport() {
    }

    protected synchronized int findFreePort() {
        try {
            final ServerSocket serverSocket = new ServerSocket(0);
            final int port = serverSocket.getLocalPort();
            serverSocket.close();
            return port;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // test support

    protected int httpPort() throws IOException {
        final Configuration configuration = configurationAdmin.getConfiguration("org.ops4j.pax.web");
        return Integer.parseInt(configuration.getProperties().get("org.osgi.service.http.port").toString());
    }

    protected Bundle findBundle(final String symbolicName) {
        for (final Bundle bundle : bundleContext.getBundles()) {
            if (symbolicName.equals(bundle.getSymbolicName())) {
                return bundle;
            }
        }
        return null;
    }

    // configuration support

    protected String karafGroupId() {
        return KARAF_GROUP_ID;
    }

    protected String karafArtifactId() {
        return KARAF_ARTIFACT_ID;
    }

    protected String karafName() {
        return KARAF_NAME;
    }

    protected Option addBootFeature(final String feature) {
        return editConfigurationFileExtend("etc/org.apache.karaf.features.cfg", "featuresBoot", "," + feature);
    }

    protected String featureRepositories() {
        final String[] featureRepositories = {
            "mvn:org.eclipse.smarthome.karaf/org.eclipse.smarthome.karaf.features/0.8.0-SNAPSHOT/xml/features",
            "mvn:org.openhab.karaf/org.openhab.karaf.features/2.0.0-SNAPSHOT/xml/features"
        };
        return String.join(",", featureRepositories);
    }

    protected Option karafTestSupportBundle() {
        return streamBundle(
            bundle()
                .add(KarafTestSupport.class)
                .set(Constants.BUNDLE_MANIFESTVERSION, "2")
                .set(Constants.BUNDLE_SYMBOLICNAME, "org.openhab.karaf-integration-tests")
                .set(Constants.EXPORT_PACKAGE, "org.openhab.karaf.testing")
                .set(Constants.IMPORT_PACKAGE, "javax.inject, org.apache.karaf.features, org.ops4j.pax.exam, org.ops4j.pax.exam.options, org.ops4j.pax.exam.util, org.ops4j.pax.tinybundles.core, org.osgi.framework, org.osgi.service.cm")
                .build()
        ).start();
    }

    protected Option[] baseConfiguration() {
        final int rmiRegistryPort = findFreePort();
        final int rmiServerPort = findFreePort();
        final int sshPort = findFreePort();
        final int httpPort = findFreePort();
        return options(
            karafDistributionConfiguration()
                .frameworkUrl(maven().groupId(karafGroupId()).artifactId(karafArtifactId()).versionAsInProject().type("tar.gz"))
                .useDeployFolder(false)
                .name(karafName())
                .unpackDirectory(new File("target/paxexam/" + getClass().getSimpleName())),
            keepRuntimeFolder(),
            logLevel(LogLevelOption.LogLevel.INFO),
            editConfigurationFileExtend("etc/org.apache.karaf.features.cfg", "featuresRepositories", "," + featureRepositories()),
            editConfigurationFilePut("etc/org.apache.karaf.management.cfg", "rmiRegistryPort", Integer.toString(rmiRegistryPort)),
            editConfigurationFilePut("etc/org.apache.karaf.management.cfg", "rmiServerPort", Integer.toString(rmiServerPort)),
            editConfigurationFilePut("etc/org.apache.karaf.shell.cfg", "sshPort", Integer.toString(sshPort)),
            editConfigurationFilePut("etc/org.ops4j.pax.web.cfg", "org.osgi.service.http.port", Integer.toString(httpPort)),
            mavenBundle().groupId("org.ops4j.pax.tinybundles").artifactId("tinybundles").versionAsInProject(),
            mavenBundle().groupId("biz.aQute.bnd").artifactId("biz.aQute.bndlib").versionAsInProject(),
            karafTestSupportBundle()
        );
    }

}
