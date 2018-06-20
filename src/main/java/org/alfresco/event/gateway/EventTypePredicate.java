/*
 * Copyright 2018 Alfresco Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 *    http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.alfresco.event.gateway;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Predicate;
import org.apache.camel.util.ObjectHelper;

/**
 * Event type predicate to evaluate the message type (retrieved via 'header') is an instance of the configured type.
 *
 * @author Jamal Kaabi-Mofrad
 */
public class EventTypePredicate implements Predicate
{
    private Expression retrievedTypeExpression;
    private Class<?> requiredType;

    public EventTypePredicate(Expression retrievedTypeExpression, Class<?> requiredType)
    {
        this.retrievedTypeExpression = retrievedTypeExpression;
        this.requiredType = requiredType;
    }

    @Override
    public boolean matches(Exchange exchange)
    {
        if (requiredType == null || retrievedTypeExpression == null)
        {
            return false;
        }
        else
        {
            String headerValue = retrievedTypeExpression.evaluate(exchange, String.class);
            if (headerValue == null)
            {
                return false;
            }
            try
            {
                return ObjectHelper.isAssignableFrom(requiredType, Class.forName(headerValue));
            }
            catch (ClassNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
