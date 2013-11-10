package org.frayer.gradle.plugins.websphere.tasks

class WsAntStopAppTask extends AbstractWsAppTask {

    static final String SERVER = 'server'
    static final String NODE = 'node'
    static final String APPLICATION_GRADLE = 'applicationName'
    static final String APPLICATION_ANT = 'application'

    String server
    String node
    String applicationName

    @Override
    public String getDescription() {
        'Stop an existing or newly installed application on a WebSphere Server or in a WebSphere Cell'
    }

    @Override
    public String getAntTaskName() {
        'wsStopApp'
    }

    @Override
    public String getAntTaskClassName() {
        'com.ibm.websphere.ant.tasks.StopApplication'
    }


    @Override
    def getApplicablePropertyNames() {
        [
            [gradleName: WAS_HOME,               antName: WAS_HOME],
            [gradleName: SERVER,                 antName: SERVER],
            [gradleName: NODE,                   antName: NODE],
            [gradleName: APPLICATION_GRADLE,     antName: APPLICATION_ANT],
            [gradleName: JAVA_PROPERTIES_GRADLE, antName: JAVA_PROPERTIES_ANT],
            [gradleName: PROFILE,                antName: PROFILE],
            [gradleName: PROFILE_NAME,           antName: PROFILE_NAME],
            [gradleName: CONNTYPE,               antName: CONNTYPE],
            [gradleName: HOST,                   antName: HOST],
            [gradleName: PORT,                   antName: PORT],
            [gradleName: USER,                   antName: USER],
            [gradleName: PASSWORD,               antName: PASSWORD],
            [gradleName: FAIL_ON_ERROR_GRADLE,   antName: FAIL_ON_ERROR_ANT],
        ]
    }

}
