package org.frayer.gradle.plugins.websphere.tasks.wsadmin;

import org.frayer.gradle.plugins.utils.AntProperty;
import org.frayer.gradle.plugins.websphere.ConnectionType;
import org.gradle.api.InvalidUserDataException;

/**
 * @author Alexey Pimenov
 */
public class WsStopApplication extends WsAdminBase {

    @AntProperty("application")
    private String applicationName;

    @AntProperty
    private String server;

    @AntProperty
    private  String node;

    @Override
    public  String getProfile() {
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

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    @Override
    public String getAntTaskName() {
        return "wsStopApplication";
    }

    @Override
    public String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.StopApplication";
    }

    @Override
    public void validate() throws InvalidUserDataException {
        super.validate();
        if(getConnectionType()==ConnectionType.NONE){
            throw new  InvalidUserDataException("Task "+getAntTaskName()+" cannot run with connectionType NONE");
        }

        if(getApplicationName()==null){
            throw new  InvalidUserDataException("application must be not null");
        }
    }
}
