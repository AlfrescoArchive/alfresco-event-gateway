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
package org.alfresco.event.gateway;

import static org.junit.Assert.assertNotNull;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.alfresco.event.gateway.config.FromRouteProperties;
import org.alfresco.event.gateway.config.ToRouteProperties;
import org.alfresco.event.gateway.util.TestUtil;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.ValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jamal Kaabi-Mofrad
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractCamelMockTest
{

    private static final ValueMatcher<Object> STREAM_POSITION_VALUE_MATCHER = (o1, o2) -> {
        final String timestampRange = "{" + Long.toString(System.currentTimeMillis()).length() + "}";
        final String regex = "[0-9]" + timestampRange + "-[A-Za-z0-9]{6}";
        // Just check that the "actual" value (o1) and expected value (o2)
        // are conforming to the defined regex. E.g: "1532903108328-ngh7oa"
        return o1.toString().matches(regex) && o2.toString().matches(regex);
    };

    private static final CustomComparator CUSTOM_JSON_COMPARATOR = new CustomComparator(JSONCompareMode.STRICT,
                new Customization("streamPosition", STREAM_POSITION_VALUE_MATCHER));


    @Autowired
    protected CamelContext camelContext;

    @Autowired
    private FromRouteProperties fromRouteProperties;

    @Autowired
    private ProducerTemplate producer;

    private MockEndpoint mockEndpoint;

    @Before
    public void setUp()
    {
        // Set producer endpoint
        producer.setDefaultEndpointUri(fromRouteProperties.getUri());
        // Construct MockEndpoint
        mockEndpoint = getMockEndpoint(getToRoute().getUri());
    }

    @After
    public void tearDown()
    {
        MockEndpoint.resetMocks(camelContext);
    }

    @Test
    public void testRoute_NodeAddedPublicEvent() throws Exception
    {
        // Set the expected number of messages
        mockEndpoint.expectedMessageCount(1);

        // Send the event
        sendEvent(getEventResourceAsString("public-nodeAddedEvent.json"));
        // Check the expected body
        checkExpectedJsonBody(getEventResourceAsString("enriched-nodeAddedEvent.json"), getFirstBody(mockEndpoint));

        // Checks that the received message count is equal to the number of messages sent
        mockEndpoint.assertIsSatisfied();
    }

    @Test
    public void testRoute_PermissionPublicEvents() throws Exception
    {
        // Set the expected number of messages
        mockEndpoint.expectedMessageCount(3);

        // Send the events
        sendEvent(getEventResourceAsString("public-permissionGrantedEvent.json"));
        sendEvent(getEventResourceAsString("public-inheritPermissionDisabledEvent.json"));
        sendEvent(getEventResourceAsString("public-inheritPermissionEnabledEvent.json"));

        // Check the expected bodies
        checkExpectedJsonBody(getEventResourceAsString("enriched-permissionGrantedEvent.json"), getBody(mockEndpoint, 0));
        checkExpectedJsonBody(getEventResourceAsString("enriched-inheritPermissionDisabledEvent.json"), getBody(mockEndpoint, 1));
        checkExpectedJsonBody(getEventResourceAsString("enriched-inheritPermissionEnabledEvent.json"), getBody(mockEndpoint, 2));

        // Checks that the received message count is equal to the number of messages sent
        mockEndpoint.assertIsSatisfied();
    }

    @Test
    public void testRoute_AuthorityPublicEvents() throws Exception
    {
        // Set the expected number of messages
        mockEndpoint.expectedMessageCount(3);

        // Send the events
        sendEvent(getEventResourceAsString("public-authorityAddedToGroupEvent.json"));
        sendEvent(getEventResourceAsString("public-authorityRemovedFromGroupEvent.json"));
        sendEvent(getEventResourceAsString("public-groupDeletedEvent.json"));

        // Check the expected bodies
        checkExpectedJsonBody(getEventResourceAsString("enriched-authorityAddedToGroupEvent.json"), getBody(mockEndpoint, 0));
        checkExpectedJsonBody(getEventResourceAsString("enriched-authorityRemovedFromGroupEvent.json"), getBody(mockEndpoint, 1));
        checkExpectedJsonBody(getEventResourceAsString("enriched-groupDeletedEvent.json"), getBody(mockEndpoint, 2));

        // Checks that the received message count is equal to the number of messages sent
        mockEndpoint.assertIsSatisfied();
    }

    @Test
    public void testRoute_RepositoryPublicEvents() throws Exception
    {
        // Set the expected number of messages
        mockEndpoint.expectedMessageCount(2);

        // Send the events
        sendEvent(getEventResourceAsString("public-transactionCommittedEvent.json"));
        sendEvent(getEventResourceAsString("public-transactionRolledBackEvent.json"));

        // Check the expected bodies
        checkExpectedJsonBody(getEventResourceAsString("enriched-transactionCommittedEvent.json"), getBody(mockEndpoint, 0));
        checkExpectedJsonBody(getEventResourceAsString("enriched-transactionRolledBackEvent.json"), getBody(mockEndpoint, 1));

        // Checks that the received message count is equal to the number of messages sent
        mockEndpoint.assertIsSatisfied();
    }

    protected void sendEvent(Object message)
    {
        assertNotNull(message);
        producer.sendBody(message);
    }

    protected MockEndpoint getMockEndpoint(String route)
    {
        assertNotNull("The route uri cannot be null.", route);
        return camelContext.getEndpoint(route, MockEndpoint.class);
    }

    protected String getFirstBody(MockEndpoint mockEndpoint)
    {
        return getBody(mockEndpoint, 0);
    }

    protected String getBody(MockEndpoint mockEndpoint, int index)
    {
        List<Exchange> list = mockEndpoint.getExchanges();
        if (list.size() <= index)
        {
            return null;
        }
        return new String(((byte[]) list.get(index).getIn().getBody()), StandardCharsets.UTF_8);
    }

    private void checkExpectedJsonBody(String expectedJsonBody, String actualJsonBody) throws Exception
    {
        JSONAssert.assertEquals(expectedJsonBody, actualJsonBody, CUSTOM_JSON_COMPARATOR);
    }

    private String getEventResourceAsString(String eventFileName) throws Exception
    {
        return TestUtil.getResourceFileAsString("events/" + eventFileName);
    }

    protected abstract ToRouteProperties getToRoute();
}
