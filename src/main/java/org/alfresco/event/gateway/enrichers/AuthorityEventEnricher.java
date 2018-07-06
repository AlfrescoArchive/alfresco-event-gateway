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
package org.alfresco.event.gateway.enrichers;

import java.util.Collections;

import org.alfresco.event.model.AuthorityResourceV1;
import org.alfresco.event.model.EventV1;
import org.alfresco.events.types.authority.AuthorityAddedToGroupEvent;
import org.alfresco.events.types.authority.AuthorityEvent;
import org.alfresco.events.types.authority.AuthorityRemovedFromGroupEvent;
import org.alfresco.events.types.authority.GroupDeletedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Jamal Kaabi-Mofrad
 */
@Component
public class AuthorityEventEnricher implements EventEnricher<AuthorityEvent, AuthorityResourceV1>
{
    @Override
    public EventV1<AuthorityResourceV1> enrich(AuthorityEvent event)
    {
        AuthorityResourceV1 resource = new AuthorityResourceV1(event.getId(), Collections.emptyList(), event.getAuthorityName());
        if (event instanceof AuthorityAddedToGroupEvent)
        {
            resource.setParentGroup(((AuthorityAddedToGroupEvent) event).getParentGroup());
        }
        else if (event instanceof AuthorityRemovedFromGroupEvent)
        {
            resource.setParentGroup(((AuthorityRemovedFromGroupEvent) event).getParentGroup());
        }
        else if (event instanceof GroupDeletedEvent)
        {
            resource.setCascade(((GroupDeletedEvent) event).isCascade());
        }

        return new EventV1<>(event.getType(), getStreamPosition(), event.getUsername(), resource);
    }
}
