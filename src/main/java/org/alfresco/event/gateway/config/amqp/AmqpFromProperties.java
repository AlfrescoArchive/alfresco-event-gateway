/*
 * Copyright 2005-2018 Alfresco Software, Ltd. All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
 */

package org.alfresco.event.gateway.config.amqp;

import org.alfresco.event.gateway.config.FromRouteProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * AMQP configuration properties.
 *
 * @author Jamal Kaabi-Mofrad
 */
@ConfigurationProperties(prefix = "messaging.from.activemq")
public class AmqpFromProperties extends AmqpProperties
{
    private final FromRouteProperties fromRoute = new FromRouteProperties();

    public FromRouteProperties getFromRoute()
    {
        return fromRoute;
    }
}