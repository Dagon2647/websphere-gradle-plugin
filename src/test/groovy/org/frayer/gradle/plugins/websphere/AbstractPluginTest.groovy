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

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

abstract class AbstractPluginTest extends Specification {

    protected Project project

    protected setup() {


        Properties properties = new Properties();
        properties.load(this.class.getResourceAsStream("/gradle.properties"));
        project = ProjectBuilder.builder().build();

        properties.each {String k,v->
            project.setProperty(k,v);
        }
        project.apply plugin: 'websphere'

    }
}
