package org.frayer.gradle.plugins.websphere.tasks.wsadmin;

/**
 * @author Alexey Pimenov
 */
public class WsStartApplication  extends WsStopApplication{

    @Override
    public String getAntTaskName() {
        return "wsStartApplication";
    }

    @Override
    public String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.StartApplication";
    }
}
