package org.frayer.gradle.plugins.websphere.tasks

import org.frayer.gradle.plugins.websphere.AbstractPluginTest
import org.frayer.gradle.plugins.websphere.ConnectionType


/**
 * @author Alexey Pimenov
 */
class WsAdminTest extends AbstractPluginTest {

    private WsAdmin task;
    def setup() {
       task = project.tasks.create("wsAdmin",WsAdmin.class);
    }


    def "Mixin properties are availible"(){
        when:
        task.profileName = "WEEE"
        then:
        task.profileName == "WEEE"
        noExceptionThrown()
    }

    def "Print help on local server"(){
        given:
        task.command = "print Help.help()"
        task.connectionType = ConnectionType.NONE
        task.profileName = "AppSrv01"
        when:
        task.execute();
        then:
        noExceptionThrown();

    }




}
