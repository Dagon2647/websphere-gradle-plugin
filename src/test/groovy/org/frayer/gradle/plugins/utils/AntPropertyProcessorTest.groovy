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

package org.frayer.gradle.plugins.utils

import org.frayer.gradle.plugins.utils.AntPropertyProcessor
import org.frayer.gradle.plugins.utils.AntProperty
import spock.lang.Specification

class AntPropertyProcessorTest  extends Specification{


    private class A{
        @AntProperty
        String propA
    }

    private class B extends A{
        @AntProperty("propC")
        String propB
    }

    private class C extends B{

        @AntProperty("propB")
        def String getPropC(){
           return "propBValue"
        }
    }


    private AntPropertyProcessor processor;

    def setup(){
       processor = new AntPropertyProcessor();
    }

    def "Must find all properties"(){
        given:
        def instance = new B(propA: "value1",propB: "value2")
        when:
        def properties = processor.getPropertyValues(instance);
        properties.hashCode();
        then:
        noExceptionThrown();
        properties.size()==2
        properties.containsKey("propA")
        properties.containsKey("propC")
    }


    def "Getters are introspected"(){
        given:
        def instance = new C(propA: "A",propB: "B");
        when:
        def properties = processor.getPropertyValues(instance);
        then:
        properties.size()==3;
        properties.containsKey("propB");
        properties.propB.value == "propBValue"
    }



}
