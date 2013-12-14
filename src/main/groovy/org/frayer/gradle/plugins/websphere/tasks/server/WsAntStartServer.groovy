package org.frayer.gradle.plugins.websphere.tasks.server

import org.frayer.gradle.plugins.utils.AntProperty

/**
 * @author Alexey Pimenov
 */
class WsAntStartServer extends WsAntServerControl{

    @AntProperty
    String script;


    @Override
    String getAntTaskName() {
        return "wsStartServer"
    }

    @Override
    String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.StartServer"
    }
}
