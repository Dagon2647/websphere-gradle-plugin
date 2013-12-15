package org.frayer.gradle.plugins.websphere.tasks.wsadmin;

import org.frayer.gradle.plugins.utils.AntProperty;
import org.frayer.gradle.plugins.websphere.WsAdminLang;
import org.frayer.gradle.plugins.websphere.tasks.WsAntRemoteTask;

import java.io.File;

/**
 * @author Alexey Pimenov
 */
public abstract class WsAdminBase extends WsAntRemoteTask {

    @AntProperty("properties")
    String javaProperties;

    @AntProperty
    String profile;

    @AntProperty
    String profileName;

    @AntProperty
    String command;

    @AntProperty
    File script;

    @AntProperty("lang")
    WsAdminLang language = WsAdminLang.JYTHON;

    @AntProperty
    String jvmMaxMemory;


}
