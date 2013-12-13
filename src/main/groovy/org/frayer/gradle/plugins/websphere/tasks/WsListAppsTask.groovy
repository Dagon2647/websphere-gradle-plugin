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

package org.frayer.gradle.plugins.websphere.tasks

import org.frayer.gradle.plugins.utils.utils.AntProperty
/**
 * @author  Alexey Pimenov
 *
 */
class WsListAppsTask extends WsAntRemoteTask{

    static final String TASK_CLASS = "com.ibm.websphere.ant.tasks.ListApplications";

    @AntProperty
    Boolean failOnError = true;

    @Override
    String getAntTaskName() {
        'wsListApps';
    }

    @Override
    String getAntTaskClassName() {
        return TASK_CLASS;
    }

    @Override
    def getApplicablePropertyNames() {
        return null
    }


}
