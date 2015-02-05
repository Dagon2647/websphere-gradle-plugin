package org.frayer.gradle.plugins.websphere.tasks.wsadmin;

import org.frayer.gradle.plugins.utils.AntProperty;

/**
 * @author Alexey Pimenov
 */
public class WsUninstallApplication extends WsAdminBase {


    @AntProperty("application")
    def String applicationName;

    @AntProperty
    private String options;



    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
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
    public String getJavaProperties() {
        return super.getJavaProperties();
    }

    @Override
    public void setJavaProperties(String javaProperties) {
        super.setJavaProperties(javaProperties);
    }

    @Override
    public String getAntTaskName() {
        return "wsUninstallApplication";
    }

    @Override
    public String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.UninstallApplication";
    }
}
