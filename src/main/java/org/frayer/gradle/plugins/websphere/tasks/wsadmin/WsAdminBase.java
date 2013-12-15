package org.frayer.gradle.plugins.websphere.tasks.wsadmin;

import org.frayer.gradle.plugins.utils.AntProperty;
import org.frayer.gradle.plugins.websphere.WsAdminLang;
import org.frayer.gradle.plugins.websphere.tasks.WsAntRemoteTask;

import java.io.File;

/**
 * @author Alexey Pimenov
 */
public abstract class WsAdminBase extends WsAntRemoteTask{

    @AntProperty("properties")
    protected String javaProperties;

    @AntProperty
    protected String profile;

    @AntProperty
    protected String profileName;

    @AntProperty
    protected String command;

    @AntProperty
    protected File script;

    @AntProperty("lang")
    protected WsAdminLang language = WsAdminLang.JYTHON;

    @AntProperty
    protected String jvmMaxMemory;

    protected String getJavaProperties() {
        return javaProperties;
    }

    protected void setJavaProperties(String javaProperties) {
        this.javaProperties = javaProperties;
    }

    protected String getProfile() {
        return profile;
    }

    protected void setProfile(String profile) {
        this.profile = profile;
    }

    protected String getProfileName() {
        return profileName;
    }

    protected void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    protected String getCommand() {
        return command;
    }

    protected void setCommand(String command) {
        this.command = command;
    }

    protected File getScript() {
        return script;
    }

    protected void setScript(File script) {
        this.script = script;
    }

    protected WsAdminLang getLanguage() {
        return language;
    }

    protected void setLanguage(WsAdminLang language) {
        this.language = language;
    }

    protected String getJvmMaxMemory() {
        return jvmMaxMemory;
    }

    protected void setJvmMaxMemory(String jvmMaxMemory) {
        this.jvmMaxMemory = jvmMaxMemory;
    }
}
