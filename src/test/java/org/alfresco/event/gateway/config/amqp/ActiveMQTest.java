/*
 * Copyright 2005-2018 Alfresco Software, Ltd. All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
 */

package org.alfresco.event.gateway.config.amqp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.alfresco.event.gateway.AbstractCamelMockTest;
import org.alfresco.event.gateway.config.ToRouteProperties;
import org.alfresco.event.gateway.config.ToRouteProperties.Branch;
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
    private static final String TARGET_CONTENT_TOPIC_NAME = "alfresco.events.target.activemq.testContent";
    private static final String TARGET_PROCESS_TOPIC_NAME = "alfresco.events.target.activemq.testProcess";
    private static final String TARGET_DEFAULT_TOPIC_NAME = "alfresco.events.target.activemq.testBaseEvent";

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
        assertEquals("amqp://" + toProperties.getAddresses() + "?timeout=3000", toProperties.getUrl());
        assertNull(toProperties.getUsername());
        assertNull(toProperties.getPassword());
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
        assertEquals("amqp://" + fromProperties.getAddresses() + "?timeout=3000", fromProperties.getUrl());
        assertNull(fromProperties.getUsername());
        assertNull(fromProperties.getPassword());
        assertNotNull(fromProperties.getFromRoute());
        assertEquals(SOURCE_TOPIC_NAME, fromProperties.getFromRoute().getTopicName());
        assertEquals("direct:" + SOURCE_TOPIC_NAME, fromProperties.getFromRoute().getUri());
    }
}
