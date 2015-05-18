smarthome
=========

This project provides [Apache Karaf Features](https://karaf.apache.org/manual/latest/users-guide/provisioning.html) for [Eclipse SmartHome](https://eclipse.org/smarthome/) and [openHAB](http://www.openhab.org).

    karaf@root()> feature:repo-add mvn:org.eclipse.smarthome.karaf/org.eclipse.smarthome.karaf.features/0.8.0-SNAPSHOT/xml/features
    karaf@root()> feature:install eclipse-smarthome

    karaf@root()> feature:repo-add mvn:org.openhab.karaf/org.openhab.karaf.features/2.0.0-SNAPSHOT/xml/features
    karaf@root()> feature:install openhab
