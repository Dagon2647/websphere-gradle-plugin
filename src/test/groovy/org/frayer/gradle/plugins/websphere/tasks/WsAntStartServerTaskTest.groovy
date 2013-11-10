package org.frayer.gradle.plugins.websphere.tasks

import org.frayer.gradle.plugins.websphere.WebSphereExtension;
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import spock.lang.IgnoreRest;
import spock.lang.Specification

class WsAntStartServerTaskTest extends Specification {

    static final String WAS_HOME_VALUE = 'wasHome value'
    static final String PROFILE_NAME_VALUE = 'profileName value'

    Project project
    WebSphereExtension webSphereExtension
    WsAntStartServerTask wsAntStartServerTask

    def setup() {
        project = ProjectBuilder.builder().build()

        project.extensions.create('websphere', WebSphereExtension)
        project.websphere {
            wasHome = WAS_HOME_VALUE
            profileName = PROFILE_NAME_VALUE
        }

        project.task(type: WsAntStartServerTask, 'wsStartServer')
        wsAntStartServerTask = project.wsStartServer
    }

    def "WsAntStartServerTask populates itself where appropriate"() {
        when:
        wsAntStartServerTask.populateApplicableProperties()

        then:
        wsAntStartServerTask.wasHome == WAS_HOME_VALUE
        wsAntStartServerTask.profileName == PROFILE_NAME_VALUE
    }

    def "WsAntStartServerTask returns the correct ANT Task Attribute Values"() {
        when:
        wsAntStartServerTask.populateApplicableProperties()
        def antAttributeValues = wsAntStartServerTask.antAttributeValues

        then:
        antAttributeValues.wasHome == WAS_HOME_VALUE
        antAttributeValues.profileName == PROFILE_NAME_VALUE
    }

    def "WsAntStartServerTask inherits properties from project.websphere"() {
        given:
        project.websphere {
            quiet = true
        }

        when:
        wsAntStartServerTask.populateApplicableProperties()

        then:
        wsAntStartServerTask.quiet == true
    }

    def "it returns unique ANT Build Script paths"() {
        when:
        wsAntStartServerTask.buildScriptCounter = 0

        then:
        wsAntStartServerTask.nextWsAntBuildScriptPath.endsWith('websphere-gradle-plugin/build-wsStartServer-1.xml')
        wsAntStartServerTask.nextWsAntBuildScriptPath.endsWith('websphere-gradle-plugin/build-wsStartServer-2.xml')
        wsAntStartServerTask.nextWsAntBuildScriptPath.endsWith('websphere-gradle-plugin/build-wsStartServer-3.xml')
    }

}
