package org.frayer.gradle.plugins.websphere.tasks.server

import org.frayer.gradle.plugins.websphere.AbstractPluginTest
import org.frayer.gradle.plugins.websphere.tasks.server.WsAntStartServer

/**
 * @author Alexey Pimenov
 */
class WsAntStartServerTest extends AbstractPluginTest {

    WsAntStartServer task;

    def setup(){
        task = project.tasks.create("wsStartServer",WsAntStartServer.class);
        task.server = "server1"
    }


    def "Starts local instance"(){
        when:
        task.execute();
        then:
        noExceptionThrown();
    }

}
