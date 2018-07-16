package org.alfresco.event.gateway.services.rest;

import static org.junit.Assert.assertEquals;

import org.alfresco.event.gateway.services.EventTopicEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EventTopicControllerTest
{
    @Autowired
    private EventTopicController eventTopicController;

    @Test
    public void getEventGlobalTopicFails() throws Exception
    {
        ResponseEntity<?> response = eventTopicController.getEventGlobalTopic();        

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("alfresco.events.allEvents", ((EventTopicEntity)((JsonBodyContentEntry<?>)response.getBody()).getEntry()).getEventTopic());
        assertEquals("amqp://localhost:5672", ((EventTopicEntity)((JsonBodyContentEntry<?>)response.getBody()).getEntry()).getBrokerUri());
    }
}