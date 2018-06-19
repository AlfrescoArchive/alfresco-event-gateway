/*
 * Copyright 2005-2018 Alfresco Software, Ltd. All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
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
