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


package org.frayer.gradle.plugins.utils;

import org.apache.commons.lang.StringUtils;
import org.gradle.api.DefaultTask;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * @author Alexey Pimenov
 */
public class AntPropertyProcessor {


    public static class AntPropertyDescriptor {

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

    public Map<String, AntPropertyDescriptor> getPropertyValues(Object instance) {

        return findAllProperties(instance);
    }


    private Map<String, AntPropertyDescriptor> findAllProperties(Object obj) {
        Map<String, AntPropertyDescriptor> result = new HashMap<String, AntPropertyDescriptor>();
        Set<PropertyInfo> propertyInfos = new HashSet<PropertyInfo>();
        for (Class type = obj.getClass(); type != Object.class; type = type.getSuperclass()) {
            propertyInfos.addAll(getPropertiesForClass(type));
        }
        for (PropertyInfo propertyInfo : propertyInfos) {


            AntProperty antPropety = propertyInfo.getAnnotation(AntProperty.class);
            if (antPropety == null) {
                continue;
            }

            String name = (antPropety.value() == null || antPropety.value().isEmpty()) ? propertyInfo.field : antPropety.value();

            Object value = propertyInfo.getValue(obj);

            result.put(name, new AntPropertyDescriptor(value, propertyInfo.field));
        }
        return result;
    }


    private Set<PropertyInfo> getPropertiesForClass(Class<?> type) {
        Set<PropertyInfo> result = new HashSet<PropertyInfo>();

        for (Method method : type.getDeclaredMethods()) {
            if (!isGetter(method)) {
                continue;

            }

            String name = method.getName();
            int prefixLength = name.startsWith("is") ? 2 : 3; // it's 'get' if not 'is'.
            String fieldName = StringUtils.uncapitalize(name.substring(prefixLength));

            result.add(new PropertyInfo(type, method, fieldName));
        }


        return result;
    }


    private static boolean isGetter(Method method) {
        return ((method.getName().startsWith("get") && method.getReturnType() != Void.TYPE)
                || (method.getName().startsWith("is") && method.getReturnType().equals(boolean.class)))
                && method.getParameterTypes().length == 0 && !Modifier.isStatic(method.getModifiers());
    }


    private class PropertyInfo {
        private Class<?> type;
        private Method getter;

        private String field;

        private PropertyInfo(Class<?> type, Method getter, String field) {
            this.type = type;
            this.getter = getter;
            this.field = field;
        }


        public Field getField() {
            Class type = this.type;
            while (type != Object.class) {
                try {
                    return type.getDeclaredField(field);
                } catch (NoSuchFieldException e) {
                    type = type.getSuperclass();
                }
            }
            return null;
        }

        public Object getValue(Object obj) {
            try {
                return getter.invoke(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            } catch (InvocationTargetException e) {
                return null;
            }
        }

        public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
            Field f = getField();
            T annotation = null;
            if (f != null) {
                annotation = f.getAnnotation(annotationClass);
            }

            if (annotation == null) {
                annotation = getter.getAnnotation(annotationClass);
            }
            return annotation;
        }


    }


}
