package org.frayer.gradle.plugins.websphere.tasks.server

import org.frayer.gradle.plugins.utils.AntProperty
import org.frayer.gradle.plugins.websphere.ConnectionType

/**
 * @author Alexey Pimenov
 */
class WsAntStopServer extends WsAntServerControl {

    @AntProperty
    ConnectionType connectionType;


    @AntProperty
    Integer port;

    @Override
    String getAntTaskName() {
        return "wsStopServer"
    }

    @Override
    String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.StopServer"
    }


}
