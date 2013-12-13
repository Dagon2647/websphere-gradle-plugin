package org.frayer.gradle.plugins.websphere.tasks

import org.frayer.gradle.plugins.utils.utils.AntPropety

/**
 * Created with IntelliJ IDEA.
 * User: apimenov
 * Date: 12.12.13
 * Time: 23:00
 *  Copyright (c) ZAO "Cinimex-Informatica"
 */
class WsListAppsTask extends WsAntRemoteTask{

    static final String TASK_CLASS = "com.ibm.websphere.ant.tasks.ListApplications";

    @AntPropety
    Boolean failOnError = true;

    @Override
    String getAntTaskName() {
        'wsListApps';
    }

    @Override
    String getAntTaskClassName() {
        return TASK_CLASS;
    }

    @Override
    def getApplicablePropertyNames() {
        return null
    }

    @Override
    boolean validate() {
        return true;
    }
}
