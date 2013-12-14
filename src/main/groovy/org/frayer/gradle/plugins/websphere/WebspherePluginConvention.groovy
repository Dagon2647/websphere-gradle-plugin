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

import org.gradle.api.InvalidUserDataException
import org.gradle.api.Project

/**
 * @author Alexey Pimenov
 * */
public class WebSpherePluginConvention {

    String wasHome = System.env.WAS_HOME
    ConnectionType wasConnectionType = ConnectionType.SOAP;
    String wasHost = "localhost"
    Integer wasPort = 8880
    String wasUser;
    String wasPassword;

    public WebSpherePluginConvention(Project project) {
        wasHome = getProjectProperty project, "wasHome";
        if (wasHome == null) {
            wasHome = System.env.WAS_HOME;
        }

        wasConnectionType = getProjectProperty(project, "wasConnectionType");

        if (wasConnectionType == null) {
            wasConnectionType = ConnectionType.SOAP;
        }

        wasHost = getProjectProperty(project, "wasHost");

        if (wasHost == null) {
            wasHost = "localhost";
        }

        try{
            def val = getProjectProperty(project, "wasPort")
            if(val!=null){
                wasPort = Integer.parseInt(val);
            }

        }catch (NumberFormatException e){
            throw new InvalidUserDataException("wasPort property must be integer or string",e);
        }

        if(wasPort==null){
            wasPort = 8880;
        }

        wasUser = getProjectProperty(project, "wasUser");

        wasPassword = getProjectProperty(project, "wasPassword");





    }


    def private static getProjectProperty(Project project, String property) {
        def result = null;
        if (project.extensions.extraProperties.has(property)) {
            result = project.extensions.extraProperties.get(property);
        } else if (project.hasProperty(property)) {
            result = project.property(result);
        }
        return result;
    }

}
