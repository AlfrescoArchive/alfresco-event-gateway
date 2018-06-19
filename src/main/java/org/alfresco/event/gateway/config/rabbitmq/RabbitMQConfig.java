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

package org.alfresco.event.gateway.config.rabbitmq;

import org.alfresco.event.gateway.config.FromRouteProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;

/**
 * Auto-configuration of the RabbitMQ connection for the source broker and the camel from-route.
 *
 * @author Jamal Kaabi-Mofrad
 */
@Configuration
@ConditionalOnProperty(prefix = "messaging.from.rabbitmq.fromRoute", name = "uri")
@EnableConfigurationProperties(RabbitMQProperties.class)
public class RabbitMQConfig
{
    private final RabbitMQProperties properties;

    @Autowired
    public RabbitMQConfig(RabbitMQProperties properties)
    {
        this.properties = properties;
    }

    @Bean
    public ConnectionFactory rabbitmqConnectionFactory()
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(properties.getHost());
        factory.setPort(properties.getPort());
        factory.setUsername(properties.getUsername());
        factory.setPassword(properties.getPassword());
        factory.setVirtualHost(properties.getVirtualHost());
        return factory;
    }

    @Bean
    public FromRouteProperties fromRouteProperties()
    {
        return properties.getFromRoute();
    }
}
