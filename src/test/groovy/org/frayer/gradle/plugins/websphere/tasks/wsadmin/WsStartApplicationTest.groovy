package org.frayer.gradle.plugins.websphere.tasks.wsadmin

import org.frayer.gradle.plugins.websphere.AbstractPluginTest
import org.frayer.gradle.plugins.websphere.ConnectionType
import org.frayer.gradle.plugins.websphere.tasks.wsadmin.WsStartApplication

/**
 * @author Alexey Pimenov
 */
class WsStartApplicationTest extends AbstractPluginTest {


    private WsStartApplication task;
    def setup() {
        task = project.tasks.create("wsAdmin",WsStartApplication.class);
    }


    def "Starts default application"(){
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
