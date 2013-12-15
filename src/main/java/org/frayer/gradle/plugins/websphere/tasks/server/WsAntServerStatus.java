package org.frayer.gradle.plugins.websphere.tasks.server;

import org.frayer.gradle.plugins.utils.AntProperty;

/**
 * @author Alexey Pimenov
 */
public class WsAntServerStatus extends WsAntServerControl{

    @AntProperty
    private Boolean all;

    @AntProperty
    private String cell;

    @AntProperty
    private String node;

    public Boolean getAll() {
        return all;
    }

    public void setAll(Boolean all) {
        this.all = all;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    @Override
    public String getAntTaskName() {
        return "wsServerStatus";
    }

    @Override
    public String getAntTaskClassName() {
        return "com.ibm.websphere.ant.tasks.ServerStatus";
    }
}
