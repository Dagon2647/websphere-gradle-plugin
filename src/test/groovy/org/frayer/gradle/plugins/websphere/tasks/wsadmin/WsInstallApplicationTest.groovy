package org.frayer.gradle.plugins.websphere.tasks.wsadmin

import org.frayer.gradle.plugins.websphere.AbstractPluginTest
import org.frayer.gradle.plugins.websphere.ConnectionType

/**
 * @author Alexey Pimenov
 */
class WsInstallApplicationTest extends AbstractPluginTest {


    private WsInstallApplication task;
    def setup() {
        task = project.tasks.create("wsInstallApp",WsInstallApplication.class);
    }


    def "Install war file"(){
        given:
        task.archivePath = "C:/TEMP/sample.war"
        task.options = "{-contextroot /sample -appname sample -MapWebModToVH{{\"Hello, World Application\" \"sample.war,WEB-INF/web.xml\" \"default_host\"}}}"
        when:
        task.execute();
        then:
        noExceptionThrown();

    }
}
