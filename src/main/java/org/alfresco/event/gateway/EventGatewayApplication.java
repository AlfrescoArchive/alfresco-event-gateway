/*
 * Copyright 2005-2018 Alfresco Software, Ltd. All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
 */

package org.alfresco.event.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jamal Kaabi-Mofrad
 */
@SpringBootApplication
public class EventGatewayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(EventGatewayApplication.class, args);
    }
}
