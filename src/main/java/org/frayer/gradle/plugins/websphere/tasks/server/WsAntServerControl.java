package org.frayer.gradle.plugins.websphere.tasks.server;

import org.frayer.gradle.plugins.utils.AntProperty;
import org.frayer.gradle.plugins.websphere.tasks.WsAntWrapperTask;
import org.gradle.api.InvalidUserDataException;

/**
 * Task with properties common to Start/Stop/Status server tasks
 * @author Alexey Pimenov
 */
public abstract class WsAntServerControl extends WsAntWrapperTask {

    @AntProperty
    private String logFile;

    @AntProperty
    private Boolean noWait = false;

    @AntProperty
    private Boolean quiet = false;

    @AntProperty
    private Boolean replaceLog = false;


    @AntProperty
    private Integer timeout;


    @AntProperty
    private Integer statusPort;


    @AntProperty
    private String server;

    @AntProperty
    private String fileEncoding;


    @AntProperty("username")
    private String user;

    @AntProperty
    private String password;


    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public Boolean getNoWait() {
        return noWait;
    }

    public void setNoWait(Boolean noWait) {
        this.noWait = noWait;
    }

    public Boolean getQuiet() {
        return quiet;
    }

    public void setQuiet(Boolean quiet) {
        this.quiet = quiet;
    }

    public Boolean getReplaceLog() {
        return replaceLog;
    }

    public void setReplaceLog(Boolean replaceLog) {
        this.replaceLog = replaceLog;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getStatusPort() {
        return statusPort;
    }

    public void setStatusPort(Integer statusPort) {
        this.statusPort = statusPort;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFileEncoding() {
        return fileEncoding;
    }

    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected void validate() {
        super.validate();
        if (getServer() == null || getServer().trim().isEmpty()) {
            throw new InvalidUserDataException("Server name must be specified in order tu run "+getAntTaskName()+" task");
        }

    }


}
