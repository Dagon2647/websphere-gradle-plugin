package org.frayer.gradle.plugins.websphere.tasks.wsadmin

import org.frayer.gradle.plugins.utils.AntProperty

/**
 * @author Alexey Pimenov
 */
class WsInstallApplication extends WsAdminBase {

    @AntProperty("ear")
    String archivePath

    @AntProperty
    String options;

    @Override
    protected void setJavaProperties(String javaProperties) {
        super.setJavaProperties(javaProperties)
    }

    @Override
    protected String getJavaProperties() {
        return super.getJavaProperties()
    }

    @Override
    protected String getProfile() {
        return super.getProfile()
    }

    @Override
    protected void setProfile(String profile) {
        super.setProfile(profile)
    }

    @Override
    String getAntTaskName() {
        return "wsInstallApp"
    }

    @Override
    String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.InstallApplication"
    }
}
