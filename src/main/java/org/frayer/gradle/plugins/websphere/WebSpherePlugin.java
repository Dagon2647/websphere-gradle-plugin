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

package org.frayer.gradle.plugins.websphere;

import org.frayer.gradle.plugins.websphere.tasks.WsAntRemoteTask;
import org.frayer.gradle.plugins.websphere.tasks.WsAntWrapperTask;
import org.frayer.gradle.plugins.websphere.tasks.server.WsAntServerControl;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.internal.ConventionMapping;
import org.gradle.api.internal.ConventionTask;
import org.gradle.api.internal.IConventionAware;

import java.util.concurrent.Callable;

/**
 * @author Alexey Pimenov
 * @author Michael Frayer
 */
public class WebSpherePlugin implements Plugin<Project> {

    public static final String PLUGIN_ID = "websphere";

    private WebSphereExtension webSphereExtension;

    private WebSpherePluginConvention wsConvention;

    private Project project;

    @Override
    public void apply(Project project) {
        this.project = project;

        webSphereExtension = new WebSphereExtension();
        project.getExtensions().add(PLUGIN_ID, webSphereExtension);
        wsConvention = new WebSpherePluginConvention(project);
        project.getConvention().getPlugins().put(PLUGIN_ID, wsConvention);

        configureDefaults();
    }


    private void configureDefaults() {
        project.getTasks().withType(WsAntWrapperTask.class, new Action<WsAntWrapperTask>() {
            @Override
            public void execute(WsAntWrapperTask task) {

                task.getConventionMapping().map("wasHome", new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        return webSphereExtension.getWasHome() == null ? wsConvention.getWasHome() : webSphereExtension.getWasHome();
                    }
                });
            }
        });


        project.getTasks().withType(WsAntRemoteTask.class, new Action<WsAntRemoteTask>() {
            @Override
            public void execute(WsAntRemoteTask task) {
                task.getConventionMapping().map("connectionType", new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        return webSphereExtension.getConnectionType() == null ? wsConvention.getWasConnectionType() : webSphereExtension.getConnectionType();
                    }
                });
                task.getConventionMapping().map("host", new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        return webSphereExtension.getHost() == null ? wsConvention.getWasHost() : webSphereExtension.getHost();
                    }
                });

                task.getConventionMapping().map("port", new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        return webSphereExtension.getPort() == null ? wsConvention.getWasPort() : webSphereExtension.getPort();
                    }
                });

                task.getConventionMapping().map("user", new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        return webSphereExtension.getUser() == null ? wsConvention.getWasUser() : webSphereExtension.getUser();
                    }
                });

                task.getConventionMapping().map("password", new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        return webSphereExtension.getPassword() == null ? wsConvention.getWasPassword() : webSphereExtension.getPassword();
                    }
                });


            }
        });

        project.getTasks().withType(WsAntServerControl.class, new Action<WsAntServerControl>() {
            @Override
            public void execute(WsAntServerControl task) {

                task.getConventionMapping().map("user", new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        return webSphereExtension.getUser() == null ? wsConvention.getWasUser() : webSphereExtension.getUser();
                    }
                });

                task.getConventionMapping().map("password", new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        return webSphereExtension.getPassword() == null ? wsConvention.getWasPassword() : webSphereExtension.getPassword();
                    }
                });
            }
        });

    }


}

