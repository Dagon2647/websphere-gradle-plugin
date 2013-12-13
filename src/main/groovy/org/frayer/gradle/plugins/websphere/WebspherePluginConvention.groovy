package org.frayer.gradle.plugins.websphere

/**
 * Created with IntelliJ IDEA.
 * User: apimenov
 * Date: 12.12.13
 * Time: 22:35
 *  Copyright (c) ZAO "Cinimex-Informatica"
 */
class WebSpherePluginConvention {

    String wasHome = System.env.WAS_HOME
    ConnectionType wasConnectionType = ConnectionType.SOAP;
    String wasHost = "localhost"
    Integer wasPort = 8880
    String wasUser = "admin"
    String wasPassword = "admin"
}
