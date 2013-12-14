/*
 *
 *  * Copyright 2009 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.frayer.gradle.plugins.websphere

import org.frayer.gradle.plugins.websphere.tasks.WsAntRemoteTask
import org.frayer.gradle.plugins.websphere.tasks.WsAntWrapperTask
import org.frayer.gradle.plugins.websphere.tasks.server.WsAntServerControl
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author Alexey Pimenov
 * @author Michael Frayer
 */
public class WebSpherePlugin implements Plugin<Project> {

    private WebSphereExtension webSphereExtension;

    private WebSpherePluginConvention wsConvention;

    private Project project;

    @Override
    public void apply(Project project) {
        this.project = project;

        webSphereExtension = new WebSphereExtension();
        project.extensions.add("websphere", webSphereExtension);
        wsConvention = new WebSpherePluginConvention(project);
        project.convention.plugins.websphere = wsConvention;

        configureDefaults();
    }


    def private configureDefaults() {
        project.tasks.withType(WsAntWrapperTask.class) { WsAntWrapperTask task ->
            task.conventionMapping.wasHome = { webSphereExtension.wasHome ?: wsConvention["wasHome"] }
        }

        project.tasks.withType(WsAntRemoteTask.class) { WsAntRemoteTask task ->
            task.conventionMapping.connectionType = {
                webSphereExtension.connectionType ?: wsConvention["wasConnectionType"]
            }

            task.conventionMapping.host = { webSphereExtension.host ?: wsConvention["wasHost"] }
            task.conventionMapping.port = { webSphereExtension.port ?: wsConvention["wasPort"] }

            task.conventionMapping.user = { webSphereExtension.user ?: wsConvention["wasUser"] }
            task.conventionMapping.password = { webSphereExtension.password ?: wsConvention["wasPassword"] }
        }

        project.tasks.withType(WsAntServerControl.class){ WsAntServerControl task->
            task.conventionMapping.username = { webSphereExtension.user ?: wsConvention["wasUser"] }
            task.conventionMapping.password = { webSphereExtension.password ?: wsConvention["wasPassword"] }
        }
    }


}
