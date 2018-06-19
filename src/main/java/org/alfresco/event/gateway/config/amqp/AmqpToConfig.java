/*
 * Copyright 2005-2018 Alfresco Software, Ltd. All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
 */

package org.alfresco.event.gateway.config.amqp;

import org.alfresco.event.gateway.config.ToRouteProperties;
import org.apache.camel.component.amqp.AMQPComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auto-configuration of the AMQP connection for the target broker and the camel to-route.
 *
 * @author Jamal Kaabi-Mofrad
 */
@Configuration
@ConditionalOnProperty(prefix = "messaging.to.activemq", name = "url")
@EnableConfigurationProperties(AmqpToProperties.class)
public class AmqpToConfig
{
    private final AmqpToProperties properties;

    @Autowired
    public AmqpToConfig(AmqpToProperties properties)
    {
        this.properties = properties;
    }

    @Bean
    public AMQPComponent amqpToConnection()
    {
        return AMQPComponentFactory.getAmqpComponent(properties.getUrl(),
                    properties.getUsername(), properties.getPassword());
    }

    @Bean
    public ToRouteProperties toRouteProperties()
    {
        return properties.getToRoute();
    }
}
