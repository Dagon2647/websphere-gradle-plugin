package org.frayer.gradle.plugins.utils;

/**
 * @author Alexey Pimenov
 */
public class AntPropertyDescriptor {

    public AntPropertyDescriptor(Object value, String gradleName) {
        this.value = value;
        this.gradleName = gradleName;
    }

    private Object value;
    private String gradleName;


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getGradleName() {
        return gradleName;
    }

    public void setGradleName(String gradleName) {
        this.gradleName = gradleName;
    }
}

