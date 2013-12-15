package org.frayer.gradle.plugins.utils

import groovy.xml.MarkupBuilder

/**
 * @author Alexey Pimenov
 */
public class AntHelper {

    public static String writeAntMarkup(String taskName,
                                        String taskClass,
                                        Map<String,Object> attributes){
        StringWriter writer = new StringWriter();
        MarkupBuilder antProject = new MarkupBuilder(writer);

        antProject.project(name: 'gradleWebSpherePlugin', default: 'default') {
            taskdef(name:taskName, classname: taskClass)
            target(name: 'default') { "${taskName}"(attributes) }
        }

        writer.flush()
        writer.close()
        return writer.toString()
    }

}
