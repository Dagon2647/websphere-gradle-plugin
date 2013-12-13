package org.frayer.gradle.plugins.websphere.tasks

import org.frayer.gradle.plugins.utils.utils.AntPropety
import org.frayer.gradle.plugins.websphere.ConnectionType

/**
 * Created with IntelliJ IDEA.
 * User: apimenov
 * Date: 12.12.13
 * Time: 22:53
 *  Copyright (c) ZAO "Cinimex-Informatica"
 */
/**
 *   Base class for all task which require local WAS installation  and MAY connect to running WAS instance
 */
public abstract class WsAntRemoteTask extends WsAntWrapperTask{

    @AntPropety
    String host;

    @AntPropety
    Integer port;

    @AntPropety(required = true,value = "connType")
    ConnectionType connectionType;

    @AntPropety
    String user;

    @AntPropety
    String password;


}
