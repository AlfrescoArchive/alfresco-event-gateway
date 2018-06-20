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

import org.alfresco.event.gateway.config.FromRouteProperties;
import org.apache.camel.component.amqp.AMQPComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auto-configuration of the AMQP component for the source broker and the camel from-route.
 *
 * @author Jamal Kaabi-Mofrad
 */
@Configuration
@ConditionalOnProperty(prefix = "messaging.from.activemq", name = "url")
@EnableConfigurationProperties(AmqpFromProperties.class)
public class AmqpFromConfig
{
    private final AmqpFromProperties properties;

    @Autowired
    public AmqpFromConfig(AmqpFromProperties properties)
    {
        this.properties = properties;
    }

    @Bean
    public AMQPComponent amqpFromConnection()
    {
        return AMQPComponentFactory.getAmqpComponent(properties.getUrl(),
                    properties.getUsername(), properties.getPassword());
    }

    @Bean
    public FromRouteProperties fromRouteProperties()
    {
        return properties.getFromRoute();
    }
}
