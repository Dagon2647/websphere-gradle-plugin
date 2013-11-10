package org.frayer.gradle.plugins.websphere.tasks

class WsAntInstallAppTask extends AbstractWsAppTask {

    static final String OPTIONS = 'options'
    static final String EAR = 'ear'

    String options;
    String ear;

    @Override
    public String getDescription() {
        'Installs a new application into a WebSphere Server or Cell'
    }

    @Override
    public String getAntTaskName() {
        'wsInstallApp'
    }

    @Override
    public String getAntTaskClassName() {
        'com.ibm.websphere.ant.tasks.InstallApplication'
    }



    @Override
    public boolean validate() {
        def valid = super.validate();
        if (ear == null) {
            logger.warn("'ear' must be provided in order for ${name} to run. This task is being skipped.")
            return false
        }
        def earFile = new File(ear)
        if (earFile.canRead()) {
            ear = earFile.absolutePath
        } else {
            logger.warn("Cannot read EAR file at ${earFile.path}")
            return false
        }
        return valid
    }

    @Override
    def getApplicablePropertyNames() {
        [
            [gradleName: WAS_HOME,               antName: WAS_HOME],
            [gradleName: OPTIONS,                antName: OPTIONS],
            [gradleName: EAR,                    antName: EAR],
            [gradleName: PROFILE,                antName: PROFILE],
            [gradleName: PROFILE_NAME,           antName: PROFILE_NAME],
            [gradleName: JAVA_PROPERTIES_GRADLE, antName: JAVA_PROPERTIES_ANT],
            [gradleName: CONNTYPE,               antName: CONNTYPE],
            [gradleName: HOST,                   antName: HOST],
            [gradleName: PORT,                   antName: PORT],
            [gradleName: USER,                   antName: USER],
            [gradleName: PASSWORD,               antName: PASSWORD],
            [gradleName: FAIL_ON_ERROR_GRADLE,   antName: FAIL_ON_ERROR_ANT],
        ]
    }

}
