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
import org.frayer.gradle.plugins.utils.PriorityToObjectPropertyPopulator
import org.frayer.gradle.plugins.utils.PropertyPopulator
import org.frayer.gradle.plugins.utils.utils.AntProperty
import org.frayer.gradle.plugins.utils.utils.AntPropertyProcessor
import org.gradle.api.DefaultTask
import org.gradle.api.InvalidUserDataException
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskExecutionException
/**
 * Base class for all tasks which require to launch ws_ant utility
 *
 * @author  Michael Frayer
 */

abstract class WsAntWrapperTask extends DefaultTask {


    int buildScriptCounter = 0
    PropertyPopulator propertyPopulator = new PriorityToObjectPropertyPopulator()
    String workingDirectory = "${temporaryDir}"


    @AntProperty(required = true)
    String wasHome



    abstract String getAntTaskName()

    abstract String getAntTaskClassName()

    @Deprecated
    abstract def getApplicablePropertyNames()

    @Override
    String getGroup() {
        'WebSphere'
    }

    @TaskAction
    def executeTask() {

        if (validate()) {
            def wsAntBuildScriptPath = getNextWsAntBuildScriptPath()
            writeAntScript(wsAntBuildScriptPath)
            executeAntScript(wsAntBuildScriptPath)
        }

    }

    def getNextWsAntBuildScriptPath() {
        buildScriptCounter++
        "${workingDirectory}/build-${antTaskName}-${buildScriptCounter}.xml"
    }

    def getPathToWsAntScript() {
        logger.info("WAS_HOME is: "+this.getWasHome())
        def wsAntPathLocation = "${this.getWasHome()}/bin"
        def wsAntFileNamePattern = ~/^ws_ant\.(sh|bat)$/

        def wasAntDirectory = new File(wsAntPathLocation)
        wasAntDirectory.listFiles().find { it.name ==~ wsAntFileNamePattern }?.absolutePath
    }

    def executeAntScript(antBuildScriptPath) {
        def wsAntScriptPath = pathToWsAntScript

        if (!wsAntScriptPath) {
            def message = "Could not locate the ws_ant.(sh|bat) script needed to run this task. Please check the value provided for 'wasHome': ${this.getWasHome()}"
            logger.error(message)
            throw new InvalidUserDataException(message)
        }

        def wsAntProc = "${pathToWsAntScript} -f ${antBuildScriptPath}".execute()
        wsAntProc.consumeProcessOutput(System.out, System.err)
        wsAntProc.waitFor()
        int exitVal = wsAntProc.exitValue();
        if(exitVal!=0){
            throw new TaskExecutionException(this,new RuntimeException("Ant executions recturn code ${exitVal}"))
        }
    }

    def writeAntScript(antBuildScriptPath) {
        project.mkdir(workingDirectory)
        def file = new File(antBuildScriptPath)
        file.createNewFile()
        file.withWriter { writer ->
            writer.write(antScriptMarkup)
        }
    }

    def getAntScriptMarkup() {
        def writer = new StringWriter()
        def antProject = new MarkupBuilder(writer)
        antProject.project(name: 'gradleWebSpherePlugin', default: 'default') {
            taskdef(name: antTaskName, classname: antTaskClassName)
            target(name: 'default') { "${antTaskName}"(antAttributeValues) }
        }
        writer.flush()
        writer.close()
        writer.toString()
    }

    def getAntAttributeValues() {
        def antAttributeValues = [:]
        def AntPropertyProcessor processor = new AntPropertyProcessor();

        def props = processor.getPropertyValues(this);

        props.each { key,value->
            antAttributeValues[key] = value.value;
        }

        return antAttributeValues
    }





    boolean validate(){
        if(this.getWasHome()==null||this.getWasHome().trim().isEmpty()){
            throw new InvalidUserDataException("wasHome must be supplied to run task")
        }
        return true;
    };
}
