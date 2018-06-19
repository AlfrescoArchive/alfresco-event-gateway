/*
 * Copyright 2005-2018 Alfresco Software, Ltd. All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
 */

package org.alfresco.event.gateway.config.kafka;

import org.alfresco.event.gateway.config.ToRouteProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auto-configuration of the Kafka route.
 *
 * @author Jamal Kaabi-Mofrad
 */
@Configuration
@ConditionalOnProperty(prefix = "messaging.to.kafka", name = "addresses")
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConfig
{
    private final KafkaProperties properties;

    @Autowired
    public KafkaConfig(KafkaProperties properties)
    {
        this.properties = properties;
    }

    @Bean
    public ToRouteProperties toRouteProperties()
    {
        return properties.getToRoute();
    }
}
