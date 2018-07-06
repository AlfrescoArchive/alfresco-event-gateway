/*
 * Copyright 2018 Alfresco Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.alfresco.event.gateway.config.amqp;

import org.alfresco.event.gateway.config.ToRouteProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * AMQP configuration properties.
 *
 * @author Jamal Kaabi-Mofrad
 */
@ConfigurationProperties(prefix = "messaging.to.activemq")
public class AmqpToProperties extends AmqpProperties
{
    private final ToRouteProperties toRoute = new ToRouteProperties();

    public ToRouteProperties getToRoute()
    {
        return toRoute;
    }
}
