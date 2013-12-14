package org.frayer.gradle.plugins.websphere.tasks.wsadmin

import org.frayer.gradle.plugins.utils.AntProperty
import org.frayer.gradle.plugins.websphere.ConnectionType
import org.gradle.api.InvalidUserDataException

/**
 * @author Alexey Pimenov
 */
class WsStopApplication extends WsAdmin {

    @AntProperty
    String application

    @AntProperty
    String server

    @AntProperty
    String node

    @Override
    String getAntTaskName() {
        return "wsStopApplication"
    }

    @Override
    String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.StopApplication"
    }

    @Override
    void validate() throws InvalidUserDataException {
        Object.validate();
        if(connectionType==ConnectionType.NONE){
            throw new  InvalidUserDataException("wsStopApplication cannot run with connectionType NONE")
        }

        if(application==null){
            throw new  InvalidUserDataException("application must be not null")
        }
    }
}
