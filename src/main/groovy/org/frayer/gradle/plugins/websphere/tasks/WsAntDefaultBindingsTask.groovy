package org.frayer.gradle.plugins.websphere.tasks

class WsAntDefaultBindingsTask extends WsAntWrapperTask {

    static final String EAR = 'ear'
    static final String OUTPUT_FILE = 'outputFile'
    static final String DEFAULT_DATA_SOURCE = 'defaultDataSource'
    static final String DB_USER = 'dbUser'
    static final String DB_PASSWORD = 'dbPassword'
    static final String DEFAULT_CONN_FACTORY = 'defaultConnectionFactory'
    static final String RES_AUTH = 'resAuth'
    static final String EJB_JNDI_PREFIX = 'ejbJndiPrefix'
    static final String VIRTUAL_HOST = 'virtualHost'
    static final String FORCE_BINDINGS = 'forceBindings'
    static final String STRATEGY = 'strategy'
    static final String FAIL_ON_ERROR_GRADLE = 'failOnError'
    static final String FAIL_ON_ERROR_ANT = 'failonerror'
    static final String EXPORT_FILE = 'exportFile'


    String ear;
    String defaultDataSource
    String dbUser
    String dbPassword
    String defaultConnectionFactory
    String resAuth
    String ejbJndiPrefix
    String virtualHost
    Boolean forceBindings
    String strategy
    Boolean failOnError
    String exportFile

    @Override
    public String getDescription() {
        'Generate default IBM WebSphere Bindings for the specified EAR file'
    }

    @Override
    public String getAntTaskName() {
        'wsDefaultBindings'
    }

    @Override
    public String getAntTaskClassName() {
        'com.ibm.websphere.ant.tasks.DefaultBindings'
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
            [gradleName: EAR,                    antName: EAR],
            [gradleName: EAR,                    antName: OUTPUT_FILE],
            [gradleName: DEFAULT_DATA_SOURCE,    antName: DEFAULT_DATA_SOURCE],
            [gradleName: DB_USER,                antName: DB_USER],
            [gradleName: DB_PASSWORD,            antName: DB_PASSWORD],
            [gradleName: DEFAULT_CONN_FACTORY,   antName: DEFAULT_CONN_FACTORY],
            [gradleName: RES_AUTH,               antName: RES_AUTH],
            [gradleName: EJB_JNDI_PREFIX,        antName: EJB_JNDI_PREFIX],
            [gradleName: VIRTUAL_HOST,           antName: VIRTUAL_HOST],
            [gradleName: FORCE_BINDINGS,         antName: FORCE_BINDINGS],
            [gradleName: STRATEGY,               antName: STRATEGY],
            [gradleName: FAIL_ON_ERROR_GRADLE,   antName: FAIL_ON_ERROR_ANT],
            [gradleName: EXPORT_FILE,            antName: EXPORT_FILE],
        ]
    }

}
