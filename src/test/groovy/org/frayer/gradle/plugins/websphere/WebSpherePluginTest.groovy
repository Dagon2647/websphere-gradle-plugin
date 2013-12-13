package org.frayer.gradle.plugins.websphere

import org.frayer.gradle.plugins.websphere.tasks.WsListAppsTask

class WebSpherePluginTest extends AbstractPluginTest{

    def "plugin defines a 'websphere' extension for the project"() {
        expect:
        project.extensions.websphere != null
    }

    def "the 'websphere' extension is configurable"() {
        given:
        project.websphere { profileName = 'testProfile' }

        expect:
        project.extensions.websphere.profileName == 'testProfile'
    }

    def "Plugin defines default conventions"(){
        given:
        def WsListAppsTask task = project.tasks.create("wsListApps",WsListAppsTask.class);
        when:
        project.wasHome = "Some WAS HOME"
        project.wasConnectionType = "RMI";
        then:
        task.wasHome == project.wasHome
        task.connectionType == ConnectionType.RMI
    }


    def "Extension overrides convention"(){
        given:
        def WsListAppsTask task = project.tasks.create("wsListApps",WsListAppsTask.class);
        when:
        project.wasPort = 9990
        project.extensions.websphere.port = 666
        then:
        task.port == 666
    }


}
