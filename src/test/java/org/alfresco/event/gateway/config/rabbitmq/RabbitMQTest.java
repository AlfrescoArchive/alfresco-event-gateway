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
