package org.frayer.gradle.plugins.websphere.tasks.server;

import org.frayer.gradle.plugins.utils.AntProperty;
import org.frayer.gradle.plugins.websphere.ConnectionType;

/**
 * @author Alexey Pimenov
 */
public class WsAntStopServer extends WsAntServerControl {

    @AntProperty
    ConnectionType connectionType;


    @AntProperty
    Integer port;

    @Override
    public String getAntTaskName() {
        return "wsStopServer";
    }

    @Override
    public String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.StopServer";
    }


}
