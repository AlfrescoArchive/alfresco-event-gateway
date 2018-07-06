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

import org.alfresco.event.model.EventV1;
import org.alfresco.event.model.ResourceV1;
import org.alfresco.events.types.RepositoryEvent;
import org.springframework.stereotype.Component;

/**
 * @author Jamal Kaabi-Mofrad
 */
@Component
public class RepositoryEventEnricher implements EventEnricher<RepositoryEvent, ResourceV1>
{
    @Override
    public EventV1<ResourceV1> enrich(RepositoryEvent event)
    {
        ResourceV1 resource = new ResourceV1(event.getId(), Collections.emptyList());

        return new EventV1<>(event.getType(), generateStreamPosition(), event.getUsername(), resource);
    }
}
