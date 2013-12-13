package org.frayer.gradle.plugins.utils

import org.frayer.gradle.plugins.utils.utils.AntPropertyProcessor
import org.frayer.gradle.plugins.utils.utils.AntPropety
import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: apimenov
 * Date: 12.12.13
 * Time: 23:32
 *  Copyright (c) ZAO "Cinimex-Informatica"
 */
class AntPropertyProcessorTest  extends Specification{


    private class A{
        @AntPropety(required = true)
        String propA
    }

    private class B extends A{
        @AntPropety("propC")
        String propB
    }

    def "Must find all properties"(){
        given:
        def AntPropertyProcessor processor = new AntPropertyProcessor()
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
}
