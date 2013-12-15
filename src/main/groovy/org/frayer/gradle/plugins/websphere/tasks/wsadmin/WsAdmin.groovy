package org.frayer.gradle.plugins.websphere.tasks.wsadmin;

/**
 * @author Alexey Pimenov
 */
public class WsAdmin extends WsAdminBase {



    @Override
    public String getAntTaskName() {
        return "wsAdmin";
    }

    @Override
    public String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.WsAdmin";
    }


}
