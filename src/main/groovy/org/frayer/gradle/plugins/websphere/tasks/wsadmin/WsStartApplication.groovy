package org.frayer.gradle.plugins.websphere.tasks.wsadmin

/**
 * @author Alexey Pimenov
 */
class WsStartApplication  extends WsStopApplication{

    @Override
    String getAntTaskName() {
        return "wsStartApplication"
    }

    @Override
    String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.StartApplication"
    }
}
