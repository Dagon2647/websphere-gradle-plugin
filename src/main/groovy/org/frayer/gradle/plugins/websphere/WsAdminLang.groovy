package org.frayer.gradle.plugins.websphere

/**
 * @author Alexey Pimenov
 */
public enum WsAdminLang {

    JYTHON,JAVASCRIPT,JACL


    def String toString(){
        return super.toString().toLowerCase();
    }

}