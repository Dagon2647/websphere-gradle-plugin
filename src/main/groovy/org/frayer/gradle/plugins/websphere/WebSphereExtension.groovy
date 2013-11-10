package org.frayer.gradle.plugins.websphere

class WebSphereExtension {
    String wasHome = System.env.WAS_HOME
    String javaProperties
    String profileName
    String server
    String conntype
    String host
    Integer port
    String user
    String password

    String applicationName

    Boolean quiet
    Boolean noWait
}
