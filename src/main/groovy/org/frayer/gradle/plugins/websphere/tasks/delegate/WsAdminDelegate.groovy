package org.frayer.gradle.plugins.websphere.tasks.delegate

import org.frayer.gradle.plugins.utils.AntProperty

/**
 * Delegate for all WsAdmin related tasks(WsAdmin and applications tasks)
 * @author Alexey Pimenov
 */


public class WsAdminDelegate {

    private String javaProperties

    private String profile

    private String profileName

    @AntProperty
    String getJavaProperties() {
        return javaProperties
    }

    void setJavaProperties(String javaProperties) {
        this.javaProperties = javaProperties
    }

    @AntProperty("properties")
    String getProfile() {
        return profile
    }

    void setProfile(String profile) {
        this.profile = profile
    }
    @AntProperty
    String getProfileName() {
        return profileName
    }

    void setProfileName(String profileName) {
        this.profileName = profileName
    }
}
