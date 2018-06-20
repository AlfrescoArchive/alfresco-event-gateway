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

package org.alfresco.event.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.alfresco.event.databind.EventObjectMapperFactory;
import org.alfresco.event.gateway.config.ToRouteProperties;
import org.alfresco.event.gateway.config.ToRouteProperties.Branch;
import org.alfresco.event.model.BaseEvent;
import org.alfresco.event.model.internal.BaseInternalEvent;
import org.alfresco.event.model.internal.ContentInternalEvent;
import org.alfresco.event.model.internal.ProcessInternalEvent;
import org.alfresco.fakeeventgenerator.CamelMessageProducer;
import org.alfresco.fakeeventgenerator.EventMaker;
import org.alfresco.fakeeventgenerator.EventMaker.EventInstance;
import org.apache.camel.CamelContext;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Jamal Kaabi-Mofrad
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractCamelMockTest
{
    protected static final Class<ContentInternalEvent> BRANCH_ON_TYPE_CONTENT_CREATED = ContentInternalEvent.class;
    protected static final Class<ProcessInternalEvent> BRANCH_ON_TYPE_PROCESS_STARTED = ProcessInternalEvent.class;

    private static final ObjectMapper MAPPER = EventObjectMapperFactory.createInstance();
    private static final String START_ROUTE = "direct:start";

    @Autowired
    protected CamelContext camelContext;
    private CamelMessageProducer producer;

    @Before
    public void setup()
    {
        producer = new CamelMessageProducer(camelContext, START_ROUTE);
    }

    @After
    public void tearDown()
    {
        MockEndpoint.resetMocks(camelContext);
    }

    @Test
    public void testToRoute() throws Exception
    {
        // Construct MockEndpoint
        MockEndpoint mockAllEventsEndpoint = getMockEndpoint(getToRoute().getUri());

        // Generate random events
        BaseInternalEvent<?> event1 = EventMaker.getRandomEvent();
        BaseInternalEvent<?> event2 = EventMaker.getRandomEvent();

        // Set the expected messages
        mockAllEventsEndpoint.expectedBodiesReceived(Arrays.asList(MAPPER.writeValueAsString(event1), MAPPER.writeValueAsString(event2)));
        // Set the expected number of messages
        mockAllEventsEndpoint.expectedMessageCount(2);

        // Send the 1st event
        sendEvent(event1);
        // Send the 2nd event
        sendEvent(event2);

        // Checks that the received message count is equal to the number of messages sent
        // Also, checks the received message body is equal to the sent message
        mockAllEventsEndpoint.assertIsSatisfied();
    }

    @Test
    public void testToRouteWithBranching() throws Exception
    {
        List<Branch> branches = getToRoute().getBranches();
        // Construct MockEndpoint for branching
        assertEquals("2 branches should be defined.", 2, branches.size());
        MockEndpoint mockContentCreatedEndpoint = getMockEndpoint(branches.get(0).getUri());
        MockEndpoint mockProcessStartedEndpoint = getMockEndpoint(branches.get(1).getUri());
        MockEndpoint mockDefaultEndpoint = getMockEndpoint(getToRoute().getDefaultBranch().getUri());

        // Construct MockEndpoint for all events (no branching)
        MockEndpoint mockAllEventsEndpoint = getMockEndpoint(getToRoute().getUri());

        // Generate events
        ContentInternalEvent contentCreatedEvent = EventInstance.CONTENT_CREATED.getEvent();
        ProcessInternalEvent processStartedEvent = EventInstance.PROCESS_STARTED.getEvent();
        BaseInternalEvent<?> baseEvent = EventInstance.BASE_EVENT.getEvent();

        // Set the expected message for the ContentCreated event type
        mockContentCreatedEndpoint.expectedBodiesReceived(Collections.singletonList(MAPPER.writeValueAsString(contentCreatedEvent)));
        // Set the expected number of messages
        mockContentCreatedEndpoint.expectedMessageCount(1);

        // Set the expected message for the ProcessStarted event type
        mockProcessStartedEndpoint.expectedBodiesReceived(Collections.singletonList(MAPPER.writeValueAsString(processStartedEvent)));
        // Set the expected number of messages
        mockProcessStartedEndpoint.expectedMessageCount(1);

        // Set the expected message for the BaseEvent event type
        mockDefaultEndpoint.expectedBodiesReceived(Collections.singletonList(MAPPER.writeValueAsString(baseEvent)));
        // Set the expected number of messages
        mockDefaultEndpoint.expectedMessageCount(1);

        // Set the expected messages
        mockAllEventsEndpoint.expectedBodiesReceived(Arrays.asList(MAPPER.writeValueAsString(contentCreatedEvent),
                    MAPPER.writeValueAsString(processStartedEvent), MAPPER.writeValueAsString(baseEvent)));
        // Set the expected number of messages
        mockAllEventsEndpoint.expectedMessageCount(3);

        // Send the 1st event
        sendEvent(contentCreatedEvent);
        // Send the 2nd event
        sendEvent(processStartedEvent);
        // Send the 3rd event
        sendEvent(baseEvent);

        // Check headers
        mockContentCreatedEndpoint.message(0).header(CamelMessageProducer.HEADER_NAME).isEqualTo(ContentInternalEvent.class.getName());
        mockProcessStartedEndpoint.message(0).header(CamelMessageProducer.HEADER_NAME).isEqualTo(ProcessInternalEvent.class.getName());
        mockDefaultEndpoint.message(0).header(CamelMessageProducer.HEADER_NAME).isEqualTo(BaseInternalEvent.class.getName());

        // Checks that the received message count is equal to the number of messages sent
        // Also, checks the received message body is equal to the sent message
        mockContentCreatedEndpoint.assertIsSatisfied();
        mockProcessStartedEndpoint.assertIsSatisfied();
        mockDefaultEndpoint.assertIsSatisfied();
        mockAllEventsEndpoint.assertIsSatisfied();
    }

    protected void sendEvent(BaseInternalEvent<?> message) throws Exception
    {
        assertNotNull(message);
        producer.send(message);
    }

    protected MockEndpoint getMockEndpoint(String route)
    {
        assertNotNull("The route uri cannot be null.", route);
        return camelContext.getEndpoint(route, MockEndpoint.class);
    }

    protected abstract ToRouteProperties getToRoute();
}
