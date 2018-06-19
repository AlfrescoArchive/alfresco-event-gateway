/*
 * Copyright 2005-2018 Alfresco Software, Ltd. All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
 */

package org.alfresco.event.gateway.config.kafka;

import org.alfresco.event.gateway.config.ToRouteProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Kafka configuration properties.
 *
 * @author Jamal Kaabi-Mofrad
 */
@ConfigurationProperties(prefix = "messaging.to.kafka")
public class KafkaProperties
{
    private final ToRouteProperties toRoute = new ToRouteProperties();
    private String host;
    private int port;
    private String addresses;

    public ToRouteProperties getToRoute()
    {
        return toRoute;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public String getAddresses()
    {
        return addresses;
    }

    public void setAddresses(String addresses)
    {
        this.addresses = addresses;
    }
}
