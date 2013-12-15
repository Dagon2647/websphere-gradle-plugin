package org.frayer.gradle.plugins.websphere.tasks.wsadmin;

import org.frayer.gradle.plugins.utils.AntProperty;
import org.frayer.gradle.plugins.websphere.ConnectionType;
import org.gradle.api.InvalidUserDataException;

/**
 * @author Alexey Pimenov
 */
public class WsStopApplication extends WsAdminBase {

    @AntProperty("application")
   String applicationName;

    @AntProperty
    String server;

    @AntProperty
    String node;



    @Override
    public String getAntTaskName() {
        return "wsStopApplication";
    }

    @Override
    public String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.StopApplication";
    }

    @Override
    public void validate() throws InvalidUserDataException {
        super.validate();
        if(getConnectionType()==ConnectionType.NONE){
            throw new  InvalidUserDataException("Task "+getAntTaskName()+" cannot run with connectionType NONE");
        }

        if(getApplicationName()==null){
            throw new  InvalidUserDataException("application must be not null");
        }
    }
}
