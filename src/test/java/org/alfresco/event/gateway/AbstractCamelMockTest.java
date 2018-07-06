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
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.alfresco.event.gateway.config.ToRouteProperties;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
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
    private static final String START_ROUTE = "direct:start";
    private static final CustomComparator CUSTOM_JSON_COMPARATOR = new CustomComparator(JSONCompareMode.LENIENT,
                new Customization("streamPosition", (obj1, obj2) -> true));

    @Autowired
    protected CamelContext camelContext;

    @EndpointInject(uri = START_ROUTE)
    private ProducerTemplate producer;

    private MockEndpoint mockEndpoint;

    @Before
    public void setUp()
    {
        // Construct MockEndpoint
        mockEndpoint = getMockEndpoint(getToRoute().getUri());
    }

    @After
    public void tearDown()
    {
        MockEndpoint.resetMocks(camelContext);
    }

    @Test
    public void testRoute_NodeAddedEvent() throws Exception
    {
        // Set the expected number of messages
        mockEndpoint.expectedMessageCount(1);

        // Send the event
        sendEvent(TestHelper.RAW_NODE_ADDED_EVENT);
        // Check the expected body
        checkExpectedJsonBody(TestHelper.PUBLIC_NODE_ADDED_EVENT, getFirstBody(mockEndpoint));

        // Checks that the received message count is equal to the number of messages sent
        mockEndpoint.assertIsSatisfied();
    }

    @Test
    public void testRoute_PermissionEvents() throws Exception
    {
        // Set the expected number of messages
        mockEndpoint.expectedMessageCount(3);

        // Send the events
        sendEvent(TestHelper.RAW_PERMISSION_GRANTED_EVENT);
        sendEvent(TestHelper.RAW_INHERIT_PERMISSION_DISABLED_EVENT);
        sendEvent(TestHelper.RAW_INHERIT_PERMISSION_ENABLED_EVENT);

        // Check the expected bodies
        checkExpectedJsonBody(TestHelper.PUBLIC_PERMISSION_GRANTED_EVENT, getBody(mockEndpoint, 0));
        checkExpectedJsonBody(TestHelper.Public_INHERIT_PERMISSION_DISABLED_EVENT, getBody(mockEndpoint, 1));
        checkExpectedJsonBody(TestHelper.PUBLIC_INHERIT_PERMISSION_ENABLED_EVENT, getBody(mockEndpoint, 2));

        // Checks that the received message count is equal to the number of messages sent
        mockEndpoint.assertIsSatisfied();
    }

    @Test
    public void testRoute_AuthorityEvents() throws Exception
    {
        // Set the expected number of messages
        mockEndpoint.expectedMessageCount(3);

        // Send the events
        sendEvent(TestHelper.RAW_AUTHORITY_ADDED_TO_GROUP_EVENT);
        sendEvent(TestHelper.RAW_AUTHORITY_REMOVED_FROM_GROUP_EVENT);
        sendEvent(TestHelper.RAW_GROUP_DELETED_EVENT);

        // Check the expected bodies
        checkExpectedJsonBody(TestHelper.PUBLIC_AUTHORITY_ADDED_TO_GROUP_EVENT, getBody(mockEndpoint, 0));
        checkExpectedJsonBody(TestHelper.PUBLIC_AUTHORITY_REMOVED_FROM_GROUP_EVENT, getBody(mockEndpoint, 1));
        checkExpectedJsonBody(TestHelper.PUBLIC_GROUP_DELETED_EVENT, getBody(mockEndpoint, 2));

        // Checks that the received message count is equal to the number of messages sent
        mockEndpoint.assertIsSatisfied();
    }

    @Test
    public void testRoute_RepositoryEvents() throws Exception
    {
        // Set the expected number of messages
        mockEndpoint.expectedMessageCount(2);

        // Send the events
        sendEvent(TestHelper.RAW_TRANSACTION_COMMITTED_EVENT);
        sendEvent(TestHelper.RAW_TRANSACTION_ROLLED_BACK_EVENT);

        // Check the expected bodies
        checkExpectedJsonBody(TestHelper.PUBLIC_TRANSACTION_COMMITTED_EVENT, getBody(mockEndpoint, 0));
        checkExpectedJsonBody(TestHelper.PUBLIC_TRANSACTION_ROLLED_BACK_EVENT, getBody(mockEndpoint, 1));

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
        // Check the field exists, as we are going to ignore it
        checkFieldExists(actualJsonBody, "streamPosition");
        // Check expected body - We ignore the streamPosition field as it is dynamically generated
        JSONAssert.assertEquals(expectedJsonBody, actualJsonBody, CUSTOM_JSON_COMPARATOR);
    }

    protected void checkFieldExists(String json, String field)
    {
        assertNotNull(json);
        assertTrue("The field \"" + field + "\" does not exist.", json.indexOf(field) > 0);
    }

    protected abstract ToRouteProperties getToRoute();
}
