package org.frayer.gradle.plugins.websphere.tasks.server

import org.frayer.gradle.plugins.websphere.AbstractPluginTest
import org.frayer.gradle.plugins.websphere.tasks.server.WsAntStopServer

/**
 * @author Alexey Pimenov
 */
class WsAntStopServerTest extends AbstractPluginTest {

    WsAntStopServer task;

    def setup(){
        task = project.tasks.create("wsStopServer",WsAntStopServer.class);
        task.server = "server1"
    }


    def "Stop local server"(){
        when:
        task.execute();
        then:
        noExceptionThrown();
    }


}
