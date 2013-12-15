package org.frayer.gradle.plugins.websphere.tasks.server

import org.frayer.gradle.plugins.utils.AntProperty
import org.frayer.gradle.plugins.websphere.tasks.WsAntWrapperTask
import org.gradle.api.InvalidUserDataException

/**
 * Task with properties common to Start/Stop/Status server tasks
 * @author Alexey Pimenov
 */
public abstract class WsAntServerControl extends WsAntWrapperTask {

    @AntProperty
    String logFile

    @AntProperty
    Boolean noWait = false;

    @AntProperty
    Boolean quiet = false;

    @AntProperty
    Boolean replaceLog = false;


    @AntProperty
    Integer timeout;


    @AntProperty
    Integer statusPort


    @AntProperty
    String server;

    @AntProperty
    String fileEncoding;


    @AntProperty
    String username;

    @AntProperty
    String password;


    void validate() {
        super.validate();
        if (getServer() == null || getServer().trim().isEmpty()) {
            throw new InvalidUserDataException("Server name must be specified in order tu run ${getAntTaskName()} task")
        }

    }


}
