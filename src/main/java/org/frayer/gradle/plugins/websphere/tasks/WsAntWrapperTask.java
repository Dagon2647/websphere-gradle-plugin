/*
 *
 *  * Copyright 2009 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.frayer.gradle.plugins.websphere.tasks;

import groovy.xml.MarkupBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tools.ant.taskdefs.condition.Os;
import org.frayer.gradle.plugins.utils.*;
import org.gradle.api.DefaultTask;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.internal.ConventionTask;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Base class for all tasks which require to launch ws_ant utility
 *
 * @author Michael Frayer
 */

public abstract class WsAntWrapperTask extends ConventionTask {

    public static final String TASK_GROUP = "WebSphere";

    private static final AntPropertyProcessor processor = new AntPropertyProcessor();


    protected Logger logger;

    public WsAntWrapperTask() {
        logger = getLogger();
    }

    @AntProperty
    private String wasHome;

    @AntProperty
    private Boolean failOnError = true;

    public String getWasHome() {
        return wasHome;
    }

    public void setWasHome(String wasHome) {
        this.wasHome = wasHome;
    }

    public Boolean getFailOnError() {
        return failOnError;
    }

    public void setFailOnError(Boolean failOnError) {
        this.failOnError = failOnError;
    }

    public abstract String getAntTaskName();

    public abstract String getAntTaskClassName();


    @Override
    public String getGroup() {
        return TASK_GROUP;
    }

    @TaskAction
    public void executeTask() {

        validate();
        try {
            File wsAntBuildScriptPath = getWsAntBuildScriptPath();
            writeAntScript(wsAntBuildScriptPath);
            executeAntScript(wsAntBuildScriptPath);
        } catch (IOException e) {
            logger.error("Error launching ws_ant", e);

        }


    }

    public File getWsAntBuildScriptPath() {
        return new File(getTemporaryDir().getAbsolutePath() + File.separatorChar + getAntTaskName() + ".xml");

    }

    private File getPathToWsAntScript() {
        logger.info("WAS_HOME is: {}", getWasHome());
        File wsAntFolder = new File(getWasHome() + File.separator + "bin");

        if (!wsAntFolder.isDirectory() || !wsAntFolder.exists()) {
            return null;
        }

        File wsAntExecutable = null;
        if (Os.isFamily(Os.FAMILY_WINDOWS)) {
            wsAntExecutable = new File(wsAntFolder, "ws_ant.bat");
        } else if (Os.isFamily(Os.FAMILY_UNIX) || Os.isFamily(Os.FAMILY_WINDOWS)) {
            wsAntExecutable = new File(wsAntFolder, "ws_ant.sh");
        } else {
            return null;
        }

        if (!wsAntExecutable.exists() || !wsAntExecutable.isFile() || !wsAntExecutable.canExecute()) {
            return null;
        }
        return wsAntExecutable.getAbsoluteFile();
    }

    protected void executeAntScript(File antScriptPath) {
        File wsAntScriptPath = getPathToWsAntScript();

        if (wsAntScriptPath == null) {
            String message = "Could not locate the ws_ant.(sh|bat) script needed to run this task. Please check the value provided for 'wasHome': " + getWasHome();
            logger.error(message);
            throw new InvalidUserDataException(message);
        }
        Process process;
        try {
            process = new ProcessBuilder().directory(antScriptPath.getParentFile())

                    .command(wsAntScriptPath.getAbsolutePath(), "-f", antScriptPath.getAbsolutePath())
                    .start();
            inheritIO(process.getInputStream(), System.out);
            inheritIO(process.getErrorStream(), System.err);
            process.waitFor();

        } catch (IOException e) {
            logger.error("Error launching ws_ant", e);
            return;
        } catch (InterruptedException e) {
            logger.error("Error launching ws_ant", e);
            return;
        }


        int exitVal = process.exitValue();
        if (exitVal != 0) {
            throw new TaskExecutionException(this, new RuntimeException("Ant executions recturn code " + exitVal));
        }
    }


    private void writeAntScript(File wsAntScriptPath) throws IOException {
        getProject().mkdir(wsAntScriptPath.getParent());

        FileUtils.writeStringToFile(wsAntScriptPath, getAntScriptMarkup());

    }

    protected String getAntScriptMarkup() throws IOException {
        StringWriter writer = new StringWriter();
        Map<String, Object> attributes = getAntAttributeValues();
        logger.info("Executing ant task {}", getAntTaskName());
        logger.debug("With attributes {}", attributes);

        return AntHelper.writeAntMarkup(getAntTaskName(), getAntTaskClassName(), attributes);
    }

    protected Map<String, Object> getAntAttributeValues() {

        return getAntAttributeValues(this);
    }

    protected final Map<String, Object> getAntAttributeValues(Object object) {
        Map<String, Object> antAttributeValues = new HashMap<String, Object>();

        Map<String, AntPropertyDescriptor> props = processor.getPropertyValues(object);

        for (Map.Entry<String, AntPropertyDescriptor> entry : props.entrySet()) {
            if (entry.getValue().getValue() == null) {
                continue;
            }
            antAttributeValues.put(entry.getKey(), entry.getValue().getValue());
        }

        return antAttributeValues;
    }


    protected void validate() throws InvalidUserDataException {
        if (this.getWasHome() == null || this.getWasHome().trim().isEmpty()) {
            throw new InvalidUserDataException("wasHome must be set to run task");
        }


    }


    private static void inheritIO(final InputStream src, final PrintStream dest) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                Scanner sc = new Scanner(src);
                while (sc.hasNextLine()) {
                    dest.println(sc.nextLine());
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
