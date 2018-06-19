/*
 * Copyright 2005-2018 Alfresco Software, Ltd. All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
 */

package org.alfresco.event.gateway.config.rabbitmq;

import org.alfresco.event.gateway.config.FromRouteProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * RabbitMQ configuration properties.
 *
 * @author Jamal Kaabi-Mofrad
 */
@ConfigurationProperties(prefix = "messaging.from.rabbitmq")
public class RabbitMQProperties
{
    private final FromRouteProperties fromRoute = new FromRouteProperties();
    private String host;
    private int port;
    private String username;
    private String password;
    private String virtualHost;

    public FromRouteProperties getFromRoute()
    {
        return fromRoute;
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

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getVirtualHost()
    {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost)
    {
        this.virtualHost = virtualHost;
    }
}