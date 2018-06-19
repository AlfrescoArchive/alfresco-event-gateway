/*
 * Copyright 2005-2018 Alfresco Software, Ltd. All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
 */

package org.alfresco.event.gateway.config.amqp;

import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

/**
 * AMQP connection factory.
 *
 * @author Jamal Kaabi-Mofrad
 */
public class AMQPComponentFactory
{
    public static AMQPComponent getAmqpComponent(String url, String username, String password)
    {
        JmsConnectionFactory jmsConnectionFactory = new JmsConnectionFactory();
        jmsConnectionFactory.setRemoteURI(url);
        jmsConnectionFactory.setUsername(username);
        jmsConnectionFactory.setPassword(password);

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(jmsConnectionFactory);

        JmsConfiguration jmsConfiguration = new JmsConfiguration();
        jmsConfiguration.setConnectionFactory(cachingConnectionFactory);
        jmsConfiguration.setCacheLevelName("CACHE_CONSUMER");

        return new AMQPComponent(jmsConfiguration);
    }
}
