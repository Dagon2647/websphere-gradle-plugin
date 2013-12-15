package org.frayer.gradle.plugins.websphere.tasks.wsadmin

import org.frayer.gradle.plugins.utils.AntProperty

/**
 * @author Alexey Pimenov
 */
class WsInstallApplication extends WsAdminBase {

    @AntProperty("ear")
    String archivePath

    @AntProperty
    private String options;

    String applicationName = project.name;


    @Override
    String getAntTaskName() {
        return "wsInstallApp"
    }

    @Override
    String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.InstallApplication"
    }

    public String getOptions() {
        if (options != null) return options;

         return "{${defaultJaclParameters}}"

    }

    def String getDefaultJaclParameters(){
        "-appname ${applicationName}"
    }
}
