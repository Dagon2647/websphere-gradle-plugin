package org.frayer.gradle.plugins.websphere.tasks.server

import org.frayer.gradle.plugins.websphere.AbstractPluginTest

/**
 * @author Alexey Pimenov
 */
class WsAntServerStatusTest extends AbstractPluginTest {

    WsAntServerStatus task;

    def setup(){
        task = project.tasks.create("wsServerStatus",WsAntServerStatus.class);
        task.server = "server1"
    }


    def "Print status of local instance"(){
        when:
        task.execute();
        then:
        noExceptionThrown();
    }


}
