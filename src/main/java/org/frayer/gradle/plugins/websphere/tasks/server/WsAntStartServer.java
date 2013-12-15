package org.frayer.gradle.plugins.websphere.tasks.server;

import org.frayer.gradle.plugins.utils.AntProperty;

/**
 * @author Alexey Pimenov
 */
public class WsAntStartServer extends WsAntServerControl{

    @AntProperty
    private String script;

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    @Override
    public String getAntTaskName() {
        return "wsStartServer";
    }

    @Override
    public String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.StartServer";
    }
}
