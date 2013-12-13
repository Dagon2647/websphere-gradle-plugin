package org.frayer.gradle.plugins.websphere

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: apimenov
 * Date: 13.12.13
 * Time: 1:10
 *  Copyright (c) ZAO "Cinimex-Informatica"
 */
abstract class AbstractPluginTest extends Specification {

    protected Project project

    protected setup() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: 'websphere'
    }
}
