package org.alfresco.event.gateway.enrichers;

import org.alfresco.event.model.EventV1;
import org.springframework.stereotype.Component;

/**
 * @author Jamal Kaabi-Mofrad
 */
@Component
public class EventV1Enricher implements EventEnricher<EventV1>
{
    @Override
    public EventV1 enrich(EventV1 event)
    {
        event.setStreamPosition(generateStreamPosition());
        return event;
    }
}
