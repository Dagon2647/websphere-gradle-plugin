package org.frayer.gradle.plugins.websphere.tasks

class WsAntUninstallAppTask extends AbstractWsAppTask {

    static final String APPLICATION_GRADLE = 'applicationName'
    static final String APPLICATION_ANT = 'application'
    static final String OPTIONS = 'options'

    String applicationName
    String options;

    @Override
    public String getDescription() {
        'Unistalls an application from a WebSphere Server or Cell'
    }

    @Override
    public String getAntTaskName() {
        'wsUninstallApp'
    }

    @Override
    public String getAntTaskClassName() {
        'com.ibm.websphere.ant.tasks.UninstallApplication'
    }

    @Override
    def getApplicablePropertyNames() {
        [
            [gradleName: WAS_HOME,               antName: WAS_HOME],
            [gradleName: APPLICATION_GRADLE,     antName: APPLICATION_ANT],
            [gradleName: OPTIONS,                antName: OPTIONS],
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
