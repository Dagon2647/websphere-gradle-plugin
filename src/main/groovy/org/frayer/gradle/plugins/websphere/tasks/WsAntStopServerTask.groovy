package org.frayer.gradle.plugins.websphere.tasks

class WsAntStopServerTask extends AbstractWsServerTask {

    static final String PORT = 'port'

    Integer port

    @Override
    public String getDescription() {
        'Stops a standalone server instance'
    }

    @Override
    public String getAntTaskName() {
        'wsStopServer'
    }

    @Override
    public String getAntTaskClassName() {
        'com.ibm.websphere.ant.tasks.StopServer'
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
            [gradleName: TIMEOUT,                antName: TIMEOUT],
            [gradleName: STATUS_PORT,            antName: STATUS_PORT],
            [gradleName: WAS_HOME,               antName: WAS_HOME],
            [gradleName: FAIL_ON_ERROR_GRADLE,   antName: FAIL_ON_ERROR_ANT],
            [gradleName: PORT,                   antName: PORT],
            [gradleName: CONNTYPE,               antName: CONNTYPE],
            [gradleName: USER_GRADLE,            antName: USER_ANT],
            [gradleName: PASSWORD,               antName: PASSWORD],
        ]
    }

}
