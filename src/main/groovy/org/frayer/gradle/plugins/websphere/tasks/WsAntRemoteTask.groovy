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

import org.frayer.gradle.plugins.utils.AntProperty
import org.frayer.gradle.plugins.websphere.ConnectionType
import org.gradle.api.InvalidUserDataException

/**
 *   Base class for all task which require local WAS installation  and MAY connect to running WAS instance
 *   @author Alexey Pimenov
 */
public abstract class WsAntRemoteTask extends WsAntWrapperTask{

    @AntProperty
    String host;

    @AntProperty
    Integer port;

    @AntProperty("connType")
    ConnectionType connectionType;

    @AntProperty
    String user;

    @AntProperty
    String password;



    @Override
    void validate() throws InvalidUserDataException {
        super.validate()
        if(getConnectionType()==null){
            throw new InvalidUserDataException("Connection type is null");
        }

        if(getConnectionType()==ConnectionType.NONE) return;

        if(getHost()==null|| getPort()==null || getPort()<=0){
            throw new InvalidUserDataException("Port: ${this.getPort()} and host: ${this.getHost()} aren't setup properly with connectionType: ${this.getConnectionType()}");
        }

    }
}
