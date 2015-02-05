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

package org.frayer.gradle.plugins.websphere.tasks
import groovy.xml.MarkupBuilder
import org.frayer.gradle.plugins.utils.AntProperty
import org.frayer.gradle.plugins.utils.AntPropertyProcessor
import org.gradle.api.InvalidUserDataException
import org.gradle.api.internal.ConventionTask
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskExecutionException
import org.gradle.process.ExecResult
import org.gradle.process.ExecSpec
import org.gradle.process.internal.ExecAction

/**
 * Base class for all tasks which require to launch ws_ant utility
 *
 * @author Michael Frayer
 */

abstract class WsAntWrapperTask extends ConventionTask {


    private static final AntPropertyProcessor processor = new AntPropertyProcessor();


    @AntProperty
    String wasHome

    @AntProperty
    Boolean failOnError = true;


    abstract String getAntTaskName()

    abstract String getAntTaskClassName()


    @Override
    String getGroup() {
        'WebSphere'
    }

    @TaskAction
    def executeTask() {

        validate()

        def wsAntBuildScriptPath = getNextWsAntBuildScriptPath()
        writeAntScript(wsAntBuildScriptPath)
        executeAntScript(wsAntBuildScriptPath)


    }

    def getNextWsAntBuildScriptPath() {

        "${temporaryDir}/build-${antTaskName}.xml"
    }

    def getPathToWsAntScript() {
        logger.info("WAS_HOME is: " + this.getWasHome())
        def wsAntPathLocation = "${this.getWasHome()}/bin"
        def wsAntFileNamePattern = ~/^ws_ant\.(sh|bat)$/

        def wasAntDirectory = new File(wsAntPathLocation)
        wasAntDirectory.listFiles().find { it.name ==~ wsAntFileNamePattern }?.absolutePath
    }

    protected executeAntScript(antBuildScriptPath) {
        def wsAntScriptPath = pathToWsAntScript

        if (!wsAntScriptPath) {
            def message = "Could not locate the ws_ant.(sh|bat) script needed to run this task. Please check the value provided for 'wasHome': ${this.getWasHome()}"
            logger.error(message)
            throw new InvalidUserDataException(message)
        }
        ExecResult result = project.exec { ExecSpec exec->
            exec.executable = wsAntScriptPath
            exec.args("-f",antBuildScriptPath)
            if(logger.isInfoEnabled()){
                exec.standardInput = System.in
                exec.standardOutput = System.out
                exec.errorOutput = System.out
            }

        }

        if (result.exitValue != 0) {
            throw new TaskExecutionException(this, new RuntimeException("Ant executions recturn code ${exitVal}"))
        }
    }


    def writeAntScript(antBuildScriptPath) {
        def file = new File(antBuildScriptPath)
        file.createNewFile()
        file.withWriter { writer ->
            writer.write(antScriptMarkup)
        }
    }

    protected getAntScriptMarkup() {
        def writer = new StringWriter()
        def antProject = new MarkupBuilder(writer)
        def attributes = antAttributeValues;
        logger.debug("Executing ant task ${antTaskClassName} with attributes ${attributes}")
        antProject.project(name: 'gradleWebSpherePlugin', default: 'default') {
            taskdef(name: antTaskName, classname: antTaskClassName)
            target(name: 'default') { "${antTaskName}"(attributes) }
        }

        writer.flush()
        writer.close()
        writer.toString()
    }

    protected Map<String,Object> getAntAttributeValues() {

        return getAntAttributeValues(this)
    }

    protected Map<String,Object> getAntAttributeValues(Object object) {
        def antAttributeValues = [:]

        def props = processor.getPropertyValues(object);

        props.each { key, value ->
            if (value.value == null) {
                return;
            }
            antAttributeValues[key] = value.value;
        }

        return antAttributeValues
    }


    void validate() throws InvalidUserDataException {
        if (this.getWasHome() == null || this.getWasHome().trim().isEmpty()) {
            throw new InvalidUserDataException("wasHome must be set to run task")
        }


    };
}

