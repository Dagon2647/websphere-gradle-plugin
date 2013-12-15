package org.frayer.gradle.plugins.websphere.tasks.wsadmin;

import org.frayer.gradle.plugins.websphere.WsAdminLang;

import java.io.File;

/**
 * @author Alexey Pimenov
 */
public class WsAdmin extends WsAdminBase {


    @Override
    public String getJavaProperties() {
        return super.getJavaProperties();
    }

    @Override
    public void setJavaProperties(String javaProperties) {
        super.setJavaProperties(javaProperties);
    }

    @Override
    public String getProfile() {
        return super.getProfile();
    }

    @Override
    public void setProfile(String profile) {
        super.setProfile(profile);
    }

    @Override
    public String getProfileName() {
        return super.getProfileName();
    }

    @Override
    public void setProfileName(String profileName) {
        super.setProfileName(profileName);
    }

    @Override
    public String getCommand() {
        return super.getCommand();
    }

    @Override
    public void setCommand(String command) {
        super.setCommand(command);
    }

    @Override
    public File getScript() {
        return super.getScript();
    }

    @Override
    public void setScript(File script) {
        super.setScript(script);
    }

    @Override
    public WsAdminLang getLanguage() {
        return super.getLanguage();
    }

    @Override
    public void setLanguage(WsAdminLang lang) {
        super.setLanguage(lang);
    }

    @Override
    public String getJvmMaxMemory() {
        return super.getJvmMaxMemory();
    }

    @Override
    public void setJvmMaxMemory(String jvmMaxMemory) {
        super.setJvmMaxMemory(jvmMaxMemory);
    }

    @Override
    public String getAntTaskName() {
        return "wsAdmin";
    }

    @Override
    public String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.WsAdmin";
    }


}
