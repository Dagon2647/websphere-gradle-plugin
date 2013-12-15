package org.frayer.gradle.plugins.websphere.tasks.wsadmin

/**
 * @author Alexey Pimenov
 */
class WsInstallWebModule extends WsInstallApplication {

    String contextRoot = "/${project.name}";

    String virtualHost = "default_host";

    String webModuleName = project.name;

    def String getDefaultJaclParameters() {
        super.defaultJaclParameters + "-contextroot ${contextRoot} -MapWebModToVH{${virtualHostMappingParams}}";
    }

    def String getVirtualHostMappingParams(){
        return "{\"${webModuleName}\" \"${new File(archivePath).name},WEB-INF/web.xml\" \"${virtualHost}\"}"
    }

}
