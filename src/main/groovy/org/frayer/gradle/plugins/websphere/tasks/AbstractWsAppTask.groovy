package org.frayer.gradle.plugins.websphere.tasks

abstract class AbstractWsAppTask extends WsAntWrapperTask {

    static final String PROFILE = 'profile'
    static final String PROFILE_NAME = 'profileName'
    static final String JAVA_PROPERTIES_GRADLE = 'javaProperties'
    static final String JAVA_PROPERTIES_ANT = 'properties'
    static final String HOST = 'host'
    static final String PORT = 'port'
    static final String USER = 'user'
    static final String PASSWORD = 'password'
    static final String FAIL_ON_ERROR_GRADLE = 'failOnError'
    static final String FAIL_ON_ERROR_ANT = 'failonerror'

    String profile
    String profileName
    String javaProperties
    String host
    Integer port
    String user
    String password
    Boolean failOnError

}
