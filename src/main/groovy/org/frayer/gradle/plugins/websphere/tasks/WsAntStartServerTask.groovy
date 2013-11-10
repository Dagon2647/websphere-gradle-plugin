package org.frayer.gradle.plugins.websphere.tasks

import groovy.xml.MarkupBuilder

import java.io.StringWriter

import org.frayer.gradle.plugins.utils.PriorityToObjectPropertyPopulator;
import org.frayer.gradle.plugins.utils.PropertyPopulator
import org.frayer.gradle.plugins.websphere.WebSphereExtension
import org.gradle.api.DefaultTask
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.tasks.TaskAction;


class WsAntStartServerTask extends AbstractWsServerTask {

    static final String SCRIPT = 'script'

    String script

    @Override
    public String getDescription() {
        'Starts a standalone server instance'
    }

    @Override
    public String getAntTaskName() {
        'wsStartServer'
    }

    @Override
    public String getAntTaskClassName() {
        'com.ibm.websphere.ant.tasks.StartServer'
    }

    @Override
    def getApplicablePropertyNames() {
        [
            [gradleName: SERVER,                 antName: SERVER],
            [gradleName: PROFILE_NAME,           antName: PROFILE_NAME],
            [gradleName: NO_WAIT,                antName: NO_WAIT],
            [gradleName: QUIET,                  antName: QUIET],
            [gradleName: LOG_FILE,               antName: LOG_FILE],
            [gradleName: REPLACE_LOG,            antName: REPLACE_LOG],
            [gradleName: TRACE,                  antName: TRACE],
            [gradleName: SCRIPT,                 antName: SCRIPT],
            [gradleName: TIMEOUT,                antName: TIMEOUT],
            [gradleName: STATUS_PORT,            antName: STATUS_PORT],
            [gradleName: USER_GRADLE,            antName: USER_ANT],
            [gradleName: PASSWORD,               antName: PASSWORD],
            [gradleName: WAS_HOME,               antName: WAS_HOME],
            [gradleName: FAIL_ON_ERROR_GRADLE,   antName: FAIL_ON_ERROR_ANT],
        ]
    }

}
