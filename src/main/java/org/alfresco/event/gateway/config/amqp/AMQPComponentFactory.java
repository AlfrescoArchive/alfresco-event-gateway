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
