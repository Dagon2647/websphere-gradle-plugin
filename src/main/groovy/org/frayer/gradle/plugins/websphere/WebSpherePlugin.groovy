package org.frayer.gradle.plugins.websphere

import org.frayer.gradle.plugins.websphere.tasks.WsAntRemoteTask
import org.frayer.gradle.plugins.websphere.tasks.WsAntWrapperTask
import org.gradle.api.Plugin
import org.gradle.api.Project

public class WebSpherePlugin implements Plugin<Project> {


    @Override
    public void apply(Project project) {
        def WebSpherePluginConvention convention = new WebSpherePluginConvention();
        project.convention.plugins.websphere = convention;
        def WebSphereExtension extension = new WebSphereExtension();
        project.extensions.add("websphere",extension);

        configureDefaults(project,convention,extension);
    }


    def private configureDefaults(Project project,WebSpherePluginConvention convention,WebSphereExtension extension){
        project.tasks.withType(WsAntWrapperTask.class){ WsAntWrapperTask task->
             task.conventionMapping.wasHome= {extension.wasHome?:convention.wasHome}
        }

        project.tasks.withType(WsAntRemoteTask.class){ WsAntRemoteTask task->
            task.conventionMapping.connectionType = {extension.connectionType?:convention.wasConnectionType}

            task.conventionMapping.host = {extension.host?:convention.wasHost}
            task.conventionMapping.port = {extension.port?:convention.wasPort}

            task.conventionMapping.user = {extension.user?:convention.wasUser}
            task.conventionMapping.password = {extension.password?:convention.wasPassword}
        }
    }
}
