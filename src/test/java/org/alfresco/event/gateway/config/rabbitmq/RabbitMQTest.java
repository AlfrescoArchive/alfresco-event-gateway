/*
 * Copyright 2005-2018 Alfresco Software, Ltd. All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
 */

package org.alfresco.event.gateway.config.rabbitmq;

import static org.alfresco.event.gateway.config.kafka.KafkaTest.assertKafkaToProperties;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.alfresco.event.gateway.AbstractCamelMockTest;
import org.alfresco.event.gateway.config.ToRouteProperties;
import org.alfresco.event.gateway.config.kafka.KafkaProperties;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Jamal Kaabi-Mofrad
 */
@ActiveProfiles("mock_from_rabbitmq_to_kafka")
public class RabbitMQTest extends AbstractCamelMockTest
{
    private static final String SOURCE_TOPIC_NAME = "start";

    @Autowired
    private RabbitMQProperties fromProperties;
    @Autowired
    private KafkaProperties toProperties;

    @Test
    public void testKafkaToProperties()
    {
        assertKafkaToProperties(toProperties);
    }

    @Test
    public void testRabbitmqFromProperties()
    {
        assertEquals("localhost", fromProperties.getHost());
        assertEquals(5672, fromProperties.getPort());
        assertEquals("dbp", fromProperties.getVirtualHost());
        assertEquals("guest", fromProperties.getUsername());
        assertEquals("guest", fromProperties.getPassword());
        assertNotNull(fromProperties.getFromRoute());
        assertEquals(SOURCE_TOPIC_NAME, fromProperties.getFromRoute().getTopicName());
        assertEquals("direct:" + SOURCE_TOPIC_NAME, fromProperties.getFromRoute().getUri());
    }

    @Override
    protected ToRouteProperties getToRoute()
    {
        return toProperties.getToRoute();
    }
}
