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

import org.frayer.gradle.plugins.websphere.tasks.WsListApps

class WebSpherePluginTest extends AbstractPluginTest{

    def "plugin defines a 'websphere' extension for the project"() {
        expect:
        project.extensions.websphere != null
    }

    def "the 'websphere' extension is configurable"() {
        given:
        project.websphere { wasHome = 'testProfile' }

        expect:
        project.extensions.websphere.wasHome == 'testProfile'
    }

    def "Plugin defines default conventions"(){
        given:
        def WsListApps task = project.tasks.create("wsListApps",WsListApps.class);
        when:
        project.wasHome = "Some WAS HOME"
        project.wasConnectionType = "RMI";
        then:
        task.wasHome == project.wasHome
        task.connectionType == ConnectionType.RMI
    }


    def "Extension overrides convention"(){
        given:
        def WsListApps task = project.tasks.create("wsListApps",WsListApps.class);
        when:
        project.wasPort = 9990
        project.extensions.websphere.port = 666
        then:
        task.port == 666
    }


}
