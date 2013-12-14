package org.frayer.gradle.plugins.websphere.tasks.wsadmin

import org.frayer.gradle.plugins.websphere.AbstractPluginTest
import org.frayer.gradle.plugins.websphere.ConnectionType
import org.frayer.gradle.plugins.websphere.tasks.wsadmin.WsStopApplication


/**
 * @author Alexey Pimenov
 */
class WsStopApplicationTest extends AbstractPluginTest {


    private WsStopApplication task;
    def setup() {
        task = project.tasks.create("wsAdmin",WsStopApplication.class);
    }


    def "Stops default application"(){
        given:
        task.command = "print Help.help()"
        task.connectionType = ConnectionType.SOAP
        task.profileName = "AppSrv01"
        task.application = "DefaultApplication"
        when:
        task.execute();
        then:
        noExceptionThrown();

    }
}
