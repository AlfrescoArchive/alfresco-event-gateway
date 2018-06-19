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

import static org.alfresco.event.gateway.config.amqp.ActiveMQTest.assertAmqpFromProperties;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.alfresco.event.gateway.AbstractCamelMockTest;
import org.alfresco.event.gateway.config.ToRouteProperties;
import org.alfresco.event.gateway.config.ToRouteProperties.Branch;
import org.alfresco.event.gateway.config.amqp.AmqpFromProperties;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Jamal Kaabi-Mofrad
 */
@ActiveProfiles("mock_from_activemq_to_kafka")
public class KafkaTest extends AbstractCamelMockTest
{
    private static final String TARGET_ALL_EVENTS_TOPIC_NAME = "alfresco.events.target.kafka.testAllEvents";
    private static final String TARGET_CONTENT_TOPIC_NAME = "alfresco.events.target.kafka.testContent";
    private static final String TARGET_PROCESS_TOPIC_NAME = "alfresco.events.target.kafka.testProcess";
    private static final String TARGET_DEFAULT_TOPIC_NAME = "alfresco.events.target.kafka.testBaseEvent";

    @Autowired
    private AmqpFromProperties fromProperties;
    @Autowired
    private KafkaProperties toProperties;

    @Test
    public void testAmqpFromProperties()
    {
        assertAmqpFromProperties(fromProperties);
    }

    @Test
    public void testKafkaToProperties()
    {
        assertKafkaToProperties(toProperties);
    }

    @Override
    protected ToRouteProperties getToRoute()
    {
        return toProperties.getToRoute();
    }

    public static void assertKafkaToProperties(KafkaProperties toProperties)
    {
        assertEquals("localhost", toProperties.getHost());
        assertEquals(9092, toProperties.getPort());
        assertEquals("localhost:9092", toProperties.getAddresses());
        assertNotNull(toProperties.getToRoute());
        assertEquals(TARGET_ALL_EVENTS_TOPIC_NAME, toProperties.getToRoute().getTopicName());
        assertEquals("mock:" + TARGET_ALL_EVENTS_TOPIC_NAME, toProperties.getToRoute().getUri());
        assertNotNull(toProperties.getToRoute().getDefaultBranch());
        List<Branch> branches = toProperties.getToRoute().getBranches();
        assertNotNull(branches);
        assertEquals("Only 2 branches were defined.", 2, branches.size());

        assertEquals(TARGET_CONTENT_TOPIC_NAME, branches.get(0).getTopicName());
        assertEquals(BRANCH_ON_TYPE_CONTENT_CREATED, branches.get(0).getOnType());
        assertEquals("mock:" + TARGET_CONTENT_TOPIC_NAME, branches.get(0).getUri());

        assertEquals(TARGET_PROCESS_TOPIC_NAME, branches.get(1).getTopicName());
        assertEquals(BRANCH_ON_TYPE_PROCESS_STARTED, branches.get(1).getOnType());
        assertEquals("mock:" + TARGET_PROCESS_TOPIC_NAME, branches.get(1).getUri());

        assertEquals(TARGET_DEFAULT_TOPIC_NAME, toProperties.getToRoute().getDefaultBranch().getTopicName());
        assertEquals("mock:" + TARGET_DEFAULT_TOPIC_NAME, toProperties.getToRoute().getDefaultBranch().getUri());
    }
}
