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



import spock.lang.Specification

class PriorityToTaskPropertyPopulatorTest extends Specification {

    PropertyPopulator populator
    Object objectToPopulate

    def setup() {
        populator = new PriorityToObjectPropertyPopulator()
        objectToPopulate = new Object() {
                    def value1
                    def value2
                    def value3
                }
    }

    def "it populates an Object with all provided properties when the object has all null properties"() {
        when:
        populator.populate(objectToPopulate, [value1: 'foo', value2: 'bar', value3: 'zap'])

        then:
        objectToPopulate.value1 == 'foo'
        objectToPopulate.value2 == 'bar'
        objectToPopulate.value3 == 'zap'
    }

    def "it does not overwrite non-null properties which exist in the object"() {
        given:
        objectToPopulate.value2 = 'predefined value'

        when:
        populator.populate(objectToPopulate, [value1: 'foo', value2: 'bar', value3: 'zap'])

        then:
        objectToPopulate.value1 == 'foo'
        objectToPopulate.value2 == 'predefined value'
        objectToPopulate.value3 == 'zap'
    }

    def "a null incoming value won't overwrite non-null properties which exist in the object"() {
        given:
        objectToPopulate.value2 = 'predefined value'

        when:
        populator.populate(objectToPopulate, [value1: 'foo', value2: null, value3: 'zap'])

        then:
        objectToPopulate.value1 == 'foo'
        objectToPopulate.value2 == 'predefined value'
        objectToPopulate.value3 == 'zap'
    }

    def "only those properties provided will get set in the target object"() {
        given:
        objectToPopulate.value2 = 'predefined value'

        when:
        populator.populate(objectToPopulate, [value2: 'bar', value3: 'zap'])

        then:
        objectToPopulate.value1 == null
        objectToPopulate.value2 == 'predefined value'
        objectToPopulate.value3 == 'zap'
    }
}
