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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.alfresco.event.gateway.AbstractCamelMockTest;
import org.alfresco.event.gateway.config.ToRouteProperties;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Jamal Kaabi-Mofrad
 */
@ActiveProfiles("mock_from_activemq_to_activemq")
public class ActiveMQTest extends AbstractCamelMockTest
{
    private static final String SOURCE_TOPIC_NAME = "start";
    private static final String TARGET_ALL_EVENTS_TOPIC_NAME = "alfresco.events.target.activemq.testAllEvents";

    @Autowired
    private AmqpFromProperties fromProperties;
    @Autowired
    private AmqpToProperties toProperties;

    @Test
    public void testAmqpFromProperties()
    {
        assertAmqpFromProperties(fromProperties);
    }

    @Test
    public void testAmqpToProperties()
    {
        assertEquals("localhost", toProperties.getHost());
        assertEquals(5672, toProperties.getPort());
        assertEquals("localhost:5672", toProperties.getAddresses());
        assertEquals("amqp://" + toProperties.getAddresses(), toProperties.getUrl());
        assertNull(toProperties.getUsername());
        assertNull(toProperties.getPassword());
        assertNotNull(toProperties.getToRoute());
        assertEquals(TARGET_ALL_EVENTS_TOPIC_NAME, toProperties.getToRoute().getTopicName());
        assertEquals("mock:" + TARGET_ALL_EVENTS_TOPIC_NAME, toProperties.getToRoute().getUri());
    }

    @Override
    protected ToRouteProperties getToRoute()
    {
        return toProperties.getToRoute();
    }

    public static void assertAmqpFromProperties(AmqpFromProperties fromProperties)
    {
        assertEquals("localhost", fromProperties.getHost());
        assertEquals(5672, fromProperties.getPort());
        assertEquals("localhost:5672", fromProperties.getAddresses());
        assertEquals("amqp://" + fromProperties.getAddresses(), fromProperties.getUrl());
        assertNull(fromProperties.getUsername());
        assertNull(fromProperties.getPassword());
        assertNotNull(fromProperties.getFromRoute());
        assertEquals(SOURCE_TOPIC_NAME, fromProperties.getFromRoute().getTopicName());
        assertEquals("direct:" + SOURCE_TOPIC_NAME, fromProperties.getFromRoute().getUri());
    }
}
