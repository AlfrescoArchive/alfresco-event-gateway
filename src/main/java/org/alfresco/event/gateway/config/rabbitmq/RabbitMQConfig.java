/*
 * Copyright 2005-2018 Alfresco Software, Ltd. All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
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
