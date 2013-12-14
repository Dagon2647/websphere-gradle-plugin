package org.frayer.gradle.plugins.websphere.tasks.server

import org.frayer.gradle.plugins.utils.AntProperty

/**
 * @author Alexey Pimenov
 */
public class WsAntServerStatus extends WsAntServerControl{

    @AntProperty
    Boolean all

    @AntProperty
    String cell

    @AntProperty
    String node

    @Override
    String getAntTaskName() {
        return "wsServerStatus"
    }

    @Override
    String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.ServerStatus"
    }
}
