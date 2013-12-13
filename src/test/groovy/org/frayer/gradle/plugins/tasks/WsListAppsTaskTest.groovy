package org.frayer.gradle.plugins.tasks

import org.frayer.gradle.plugins.websphere.AbstractPluginTest
import org.frayer.gradle.plugins.websphere.tasks.WsListAppsTask

/**
 * Created with IntelliJ IDEA.
 * User: apimenov
 * Date: 13.12.13
 * Time: 1:12
 *  Copyright (c) ZAO "Cinimex-Informatica"
 */
class WsListAppsTaskTest extends AbstractPluginTest{

    WsListAppsTask task;

    @Override
    def setup() {
        task = project.tasks.create("wsListApps",WsListAppsTask.class);
    }


    def "It must fucking work"(){
       when:
       task.execute();
       then:
       noExceptionThrown();
    }
}
