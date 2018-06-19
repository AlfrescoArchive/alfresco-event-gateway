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
