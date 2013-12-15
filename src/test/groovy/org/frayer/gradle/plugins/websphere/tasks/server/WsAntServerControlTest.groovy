package org.frayer.gradle.plugins.websphere.tasks.server

import org.frayer.gradle.plugins.websphere.AbstractPluginTest
import spock.lang.Stepwise

/**
 * @author Alexey Pimenov
 */

@Stepwise
class WsAntServerControlTest extends AbstractPluginTest {

    WsAntStartServer start;

    WsAntStopServer stop;

    WsAntServerStatus status;

    def setup() {
        status = project.tasks.create("wsServerStatus", WsAntServerStatus.class);
        stop = project.tasks.create("wsStopServer", WsAntStopServer.class);
        start = project.tasks.create("wsStartServer", WsAntStartServer.class);

        project.tasks.withType(WsAntServerControl.class) { task ->
            task.server = "server1"
        }

    }

    def "Stop local server"(){
        when:
        stop.execute();
        then:
        noExceptionThrown();
    }

    def "Starts local instance"(){
        when:
        start.execute();
        then:
        noExceptionThrown();
    }


    def "Print status of local instance"(){
        when:
        status.execute();
        then:
        noExceptionThrown();
    }




}
