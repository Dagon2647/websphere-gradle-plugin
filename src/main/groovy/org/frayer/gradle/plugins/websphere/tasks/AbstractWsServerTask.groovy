package org.frayer.gradle.plugins.websphere.tasks

abstract class AbstractWsServerTask extends WsAntWrapperTask {

    static final String SERVER = 'server'
    static final String PROFILE_NAME = 'profileName'
    static final String NO_WAIT = 'noWait'
    static final String QUIET = 'quiet'
    static final String LOG_FILE = 'logFile'
    static final String REPLACE_LOG = 'replaceLog'
    static final String TRACE = 'trace'
    static final String TIMEOUT = 'timeout'
    static final String STATUS_PORT = 'statusPort'
    static final String USER_GRADLE = 'user'
    static final String USER_ANT = 'username'
    static final String PASSWORD = 'password'
    static final String FAIL_ON_ERROR_GRADLE = 'failOnError'
    static final String FAIL_ON_ERROR_ANT = 'failonerror'

    String server
    String profileName
    Boolean noWait
    Boolean quiet
    String logFile
    Boolean replaceLog
    Boolean trace
    Integer timeout
    Integer statusPort
    String user
    String password
    Boolean failOnError

}
