package org.frayer.gradle.plugins.websphere.tasks.wsadmin

import org.frayer.gradle.plugins.utils.AntProperty
import org.frayer.gradle.plugins.websphere.WsAdminLang
import org.frayer.gradle.plugins.websphere.tasks.WsAntRemoteTask

/**
 * @author Alexey Pimenov
 */
public class WsAdmin extends WsAntRemoteTask {

    //@Delegate
    //private WsAdminDelegate wsAdminDelegate = new WsAdminDelegate()

    @AntProperty("properties")
    String javaProperties

    @AntProperty
    String profile

    @AntProperty
    String profileName

    @AntProperty
    String command

    @AntProperty
    File script

    @AntProperty
    WsAdminLang lang = WsAdminLang.JYTHON;

    @AntProperty
    String jvmMaxMemory;

    @Override
    String getAntTaskName() {
        return "wsAdmin"
    }

    @Override
    String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.WsAdmin"
    }

    /*
    @Override
    def getAntAttributeValues() {
        return super.getAntAttributeValues() + getAntAttributeValues(wsAdminDelegate);
    } */
}
