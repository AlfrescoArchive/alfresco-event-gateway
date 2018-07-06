/*
 * Copyright 2018 Alfresco Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.alfresco.event.gateway;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

/**
 * A Predicate to check if the given object is of a particular type.
 *
 * @author Jamal Kaabi-Mofrad
 */
public class TypePredicate implements Predicate
{
    private Class<?> requiredType;

    public TypePredicate(Class<?> requiredType)
    {
        this.requiredType = requiredType;
    }

    @Override
    public boolean matches(Exchange exchange)
    {
        Object body = exchange.getIn().getBody();
        return requiredType.isInstance(body);
    }
}
